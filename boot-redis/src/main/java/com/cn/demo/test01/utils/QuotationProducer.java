//package com.cn.demo.test01.utils;
//
//import java.text.DecimalFormat;
//import java.util.Arrays;
//import java.util.Properties;
//import java.util.Random;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.junit.jupiter.api.Test;
//
//import com.cn.demo.test01.pojo.StockQuotationInfo;
//
//
//
//
///**
// * 股票行情生产者实现类
// * @author lenovo
// *
// */
//public class QuotationProducer {
//
//	 /** 设置实例生产消息的总数 */
//  /*  private static final int MSG_SIZE = 100;
//    *//** 主题名称 *//*
//    private static final String TOPIC = "stock-quotation";
//    *//** Kafka 集群 *//*
//    private static final String BROKER_LIST = 
//    "106.12.215.254:9092";
//    private static KafkaProducer<String, String> producer = null;
//    static {
//        // 1.构造用于实例化 KafkaProducer 的 Properties 信息
//    	 Properties props = new Properties();
// 	   	 props.put("bootstrap.servers", "106.12.215.254:9092");
// 	   	 props.put("acks", "all");
// 	   	 props.put("batch.size", 16384);
// 	   	 props.put("linger.ms", 1);
// 	   	 props.put("buffer.memory", 33554432);
// 	   	 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
// 	   	 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        // 2.初始化一个 KafkaProducer
//        producer = new KafkaProducer<String, String>(props);
//    }
//*/
//
//    /**
//     * 生产股票行情信息
//     * @return
//     */
//    private static StockQuotationInfo createQuotationInfo() {
//        StockQuotationInfo quotationInfo = new StockQuotationInfo();
//        // 随机产生1到10之间的整数，然后与600100相加组成股票代码
//        Random r = new Random();
//        Integer stockCode = 600100 + r.nextInt(10);
//        // 随机产生一个0到1之间的浮点数
//        float random = (float) Math.random();
//        // 设置涨跌规则
//        if (random / 2 < 0.5) {
//            random = -random;
//        }
//        DecimalFormat decimalFormat = new DecimalFormat(".00");// 设置保存两位有效数字
//        quotationInfo.setCurrentPrice(Float.valueOf(decimalFormat.format(11 + 
//        random)));// 设置最新价在11元浮动
//        quotationInfo.setPreClosePrice(11.80f);// 设置昨日收盘价为固定值
//        quotationInfo.setOpenPrice(11.5f);// 设置开盘价
//        quotationInfo.setLowPrice(10.5f);// 设置最低价，并不考虑10%限制，
//                                         //以及当前价是否已是最低价
//        quotationInfo.setHighPrice(12.5f);// 设置最高价，并不考虑10%限制，
//                                          //以及当前价是否已是最高价    
//        quotationInfo.setStockCode(stockCode.toString());
//        quotationInfo.setTradeTime(System.currentTimeMillis());
//        quotationInfo.setStockName("股票-" + stockCode);
//        return quotationInfo;
//    }
//
//    @Test
//    public void consumer() {
//    	 Properties props = new Properties(); 
//    	 // 服务器ip:端口号，集群用逗号分隔
//    	 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "106.12.215.254:9092"); 
//    	 // 消费者指定组，名称可以随意，注意相同消费组中的消费者只能对同一个分区消费一次 
//    	 props.put(ConsumerConfig.GROUP_ID_CONFIG, "test"); 
//    	 // 是否启用自动提交，默认true 
//    	 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); 
//    	 // 自动提交间隔时间1s 
//    	 props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000); 
//    	 // key反序列化指定类
//    	 props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
//    	 // value反序列化指定类，注意生产者与消费者要保持一致，否则解析出问题 
//    	 props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); 
//    	 // 消费者对象
//    	 KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props); 
//    	 kafkaConsumer.subscribe(Arrays.asList("stock-quotation")); 
//    	 ConsumerRecords<String, String> records = kafkaConsumer.poll(1); 
//    	 for (ConsumerRecord<String, String> record : records) { 
//    		 System.out.printf("offset = %d, value = %s", record.offset(), record.value()); System.out.println();
//    		 
//    	 } 
//    	 
//    }
//    
//    
//    @Test
//    public void consumer2() {
//    	 Properties props = new Properties(); 
//    	 props.put("bootstrap.servers", "106.12.215.254:9092"); 
//    	 props.put("group.id", "group-1");
//    	 props.put("enable.auto.commit", "true"); 
//    	 props.put("auto.commit.interval.ms", "1000"); 
//    	 props.put("auto.offset.reset", "earliest"); 
//    	 props.put("session.timeout.ms", "30000"); 
//    	 props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//    	 props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//    	 KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props); 
//    	 kafkaConsumer.subscribe(Arrays.asList("stock-quotation")); 
//    	 while (true) { 
//    		 ConsumerRecords<String, String> records = kafkaConsumer.poll(100); 
//    		 for (ConsumerRecord<String, String> record : records) { 
//    			 System.out.printf("offset = %d, value = %s", record.offset(), record.value()); System.out.println(); 
//    			 }
//    	 }
//    }
//    public static void main(String[] args) {
//    	 Properties props = new Properties();
//    	 props.put("bootstrap.servers", "106.12.215.254:9092");
//    	 props.put("acks", "all");
//    	 props.put("delivery.timeout.ms", 30000);
//    	 props.put("batch.size", 16384);
//    	 props.put("linger.ms", 1);
//    	 props.put("buffer.memory", 33554432);
//    	 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//    	 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//    	 props.put("metadata.max.age.ms", 3000);
//    	 Producer<String, String> producer = new KafkaProducer<>(props);
//    	 producer.send(new ProducerRecord<String, String>("stock-quotation", "hw", "hello world"));
//    	 System.out.println("success");
//    	 producer.close();
//   // 	 producer.close();
////        ProducerRecord<String, String> record = null;
////        StockQuotationInfo quotationInfo = null;
////        try {
////            int num = 0;
////            for (int i = 0; i < MSG_SIZE; i++) {
////                quotationInfo = createQuotationInfo();
////                record = new ProducerRecord<String, String>(TOPIC, null, 
////                quotationInfo.getTradeTime(),quotationInfo.getStockCode(),
////                quotationInfo.toString());
////                producer.send(record);// 异步发送消息
////                if (num++ % 10 == 0) {
////                    Thread.sleep(2000L);// 休眠2s
////                }
////            }
////        } catch (InterruptedException e) {
////           e.printStackTrace();
////        } finally {
////            producer.close();
////        }
//    }
////	private static final int MSG_SIZE = 10;
////	
////	private static final String TOPIC = "stock-topic2";
////	private static final String BROKER = "106.12.215.254:9092";
////	private static KafkaProducer<String, String> kafkaProducer = null;
////	
////	static {
////		Properties configs =  initConfig();
////		kafkaProducer = new  KafkaProducer<String, String>(configs);
////	}
////	/**////
////	 * 初始化kafka配置
////	 * @return
////	 */
////	 private static Properties initConfig() {
////	        Properties properties = new Properties();
////	        // Kafka broker
////	        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER);
////	        // 设置序列化类
////	        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
////	        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
////	                       StringSerializer.class.getName());
////	        return properties;
////	    }
////	 
////	 public static StockQuotationInfo createQuotationInfo() {
////		 
////		 StockQuotationInfo info = new StockQuotationInfo();
////		 Random r = new Random();
////		 Integer stockCode = 60010 + r.nextInt(10);
////		 float randoom = (float)Math.random();
////		 if(randoom / 2 <0.5) {
////			 
////			 randoom --;
////		 }
////		 DecimalFormat format = new DecimalFormat("00");
////		 info.setStockCode(stockCode.toString());
////		 info.setCurrentPrice(Float.valueOf(format.format(11+randoom)));
////		 info.setPreClosePrice(11.80f);
////		 info.setOpenPrice(11.50f);
////		 info.setLowPrice(10.5f);
////		 info.setHighPrice(12.5f);
////		 info.setTradeTime(System.currentTimeMillis());
////		 info.setStockName("股票-"+stockCode);
////		 return info;
////	 }
////	 
////	 public static void main(String[] args) {
////		 ProducerRecord<String, String> record = null;
////	        StockQuotationInfo quotationInfo = null;
////	        try {
////	            int num = 0;
////	            for (int i = 0; i < MSG_SIZE; i++) {
////	                quotationInfo = createQuotationInfo();
////	                record = new ProducerRecord<String, String>(TOPIC, null, 
////	                quotationInfo.getTradeTime(),quotationInfo.getStockCode(),
////	                quotationInfo.toString());
////	                kafkaProducer.send(record);// 异步发送消息
////	                if (num++ % 10 == 0) {
////	                    Thread.sleep(2000L);// 休眠2s
////	                }
////	            }
////	        } catch (InterruptedException e) {
////	           e.printStackTrace();
////	        } finally {
////	            kafkaProducer.close();
////	        }
////	    }
////	 
//	
//}
