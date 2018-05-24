package com.opensoc.indexing;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class Test {
	public static void main(String[] args) throws UnknownHostException {
		 Settings setting = Settings.settingsBuilder()
				                        .put("cluster.name", "es")//设置集群名称
				                          .put("tclient.transport.sniff", true).build();//自动嗅探整个集群的状
		 TransportClient client  = TransportClient.builder().settings(setting).build();
		client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.239.112"), 9300));
		BulkRequestBuilder bulkRequest = client.prepareBulk();

		IndexRequestBuilder a = client.prepareIndex("sourcefire_index", "sourcefire_doc");
		a.setSource("111");
		bulkRequest.add(a);

		BulkResponse resp = bulkRequest.execute().actionGet();
	}
}
