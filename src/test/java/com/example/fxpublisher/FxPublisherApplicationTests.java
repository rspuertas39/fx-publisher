package com.example.fxpublisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import com.example.fxpublisher.services.FxPriceListener;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest(classes = FxPublisherApplication.class)
class FxPublisherApplicationTests {

	@Autowired
	private FxPriceListener listener;

	@Autowired
    private ResourceLoader resourceLoader;

	/** The raw prices read from the file. */
	private static List<String> rawPrices;

	/** Our file with the prices. */
	private final String FILE_NAME = "classpath:prices.csv";

	/** We publish every 5 seconds. */
	private final int PUBLICATION_PERIOD = 5;

	private ScheduledExecutorService pricePublisher;

	/** Latch to wait for ever. */
	private CountDownLatch latch = new CountDownLatch(1);

	/** The index of the list. */
	private int counter = 0;

	@BeforeEach
	public void initEnvironment() throws IOException {
		init();
		pricePublisher = Executors.newScheduledThreadPool(1);
	}

	@Test
	public void test() throws InterruptedException {
		log.info("About to subscribe.");
		subscribe();
		// To wait for ever.
		latch.await();
	}

	private void init() throws IOException {
		rawPrices = new ArrayList<>();

		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			fileReader = new FileReader(resourceLoader.getResource(FILE_NAME).getFile());
			reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			while (line != null) {
				rawPrices.add(line);
				line = reader.readLine();
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}

	public void subscribe() {
		pricePublisher.scheduleAtFixedRate(() -> {
			String priceToPublish = rawPrices.get(counter);
			log.info("Publishing price with index [{}].", counter);
			if (counter == rawPrices.size() - 1) {
				counter = 0;
			} else {
				counter++;
			}
			listener.onMessage(priceToPublish);
		}, 0, PUBLICATION_PERIOD, TimeUnit.SECONDS);
	}
}
