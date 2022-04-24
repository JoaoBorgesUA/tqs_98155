package com.example.HW1;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {
    private Client client = new Client();

    @Test
    public void testGetURL_Valid() throws IOException, InterruptedException {
        assertThat(client.GetData("http://google.com")).isNotEmpty();
    }
}
