package com.mathew.es.transclient;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESTransportClient {

	TransportClient esClient;

	private void initClient() {
		try {
			Settings setting = Settings.builder().put("cluster.name", "elasticsearch").build();
			esClient = new PreBuiltTransportClient(setting)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			String[] indices = esClient.admin().indices().prepareGetIndex().setFeatures().get().getIndices();
			System.out.println(indices);
			System.out.println(esClient.nodeName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ESTransportClient().initClient();
	}

}
