//package com.cn.demo.test01.utils;
//
//import java.text.DecimalFormat;
//import java.util.Properties;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.log4j.Logger;
//
//import com.cn.demo.test01.pojo.StockQuotationInfo;
//
//import scala.util.Random;
//
//public class Producer {
//	  private static final Logger LOG = Logger.getLogger(QuotationProducer.class);
//	    /** 设置实例生产消息的总数 */
//	    private static final int MSG_SIZE = 100;
//	    /** 主题名称 */
//	    private static final String TOPIC = "stock-quotation";
//	    /** Kafka 集群 */
//	    private static final String BROKER_LIST = 
//	    "106.12.215.254:9092";
//	    private static KafkaProducer<String, String> producer = null;
//	    static {
//	        // 1.构造用于实例化 KafkaProducer 的 Properties 信息
//	        Properties configs = initConfig();
//	        // 2.初始化一个 KafkaProducer
//	        producer = new KafkaProducer<String, String>(configs);
//	    }
//
//	    /**
//	     * 初始化 Kafka 配置
//	     * @return
//	     */
//	    private static Properties initConfig() {
//	        Properties properties = new Properties();
//	        // Kafka broker列表
//	        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
//	        // 设置序列化类
//	        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
//	                       StringSerializer.class.getName());
//	        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
//	                       StringSerializer.class.getName());
//	        return properties;
//	    }
//
//	    /**
//	     * 生产股票行情信息
//	     * @return
//	     */
//	    private static StockQuotationInfo createQuotationInfo() {
//	        StockQuotationInfo quotationInfo = new StockQuotationInfo();
//	        // 随机产生1到10之间的整数，然后与600100相加组成股票代码
//	        Random r = new Random();
//	        Integer stockCode = 600100 + r.nextInt(10);
//	        // 随机产生一个0到1之间的浮点数
//	        float random = (float) Math.random();
//	        // 设置涨跌规则
//	        if (random / 2 < 0.5) {
//	            random = -random;
//	        }
//	        DecimalFormat decimalFormat = new DecimalFormat(".00");// 设置保存两位有效数字
//	        quotationInfo.setCurrentPrice(Float.valueOf(decimalFormat.format(11 + 
//	        random)));// 设置最新价在11元浮动
//	        quotationInfo.setPreClosePrice(11.80f);// 设置昨日收盘价为固定值
//	        quotationInfo.setOpenPrice(11.5f);// 设置开盘价
//	        quotationInfo.setLowPrice(10.5f);// 设置最低价，并不考虑10%限制，
//	                                         //以及当前价是否已是最低价
//	        quotationInfo.setHighPrice(12.5f);// 设置最高价，并不考虑10%限制，
//	                                          //以及当前价是否已是最高价    
//	        quotationInfo.setStockCode(stockCode.toString());
//	        quotationInfo.setTradeTime(System.currentTimeMillis());
//	        quotationInfo.setStockName("股票-" + stockCode);
//	        return quotationInfo;
//	    }
//
//	    public static void main(String[] args) {
//	        ProducerRecord<String, String> record = null;
//	        StockQuotationInfo quotationInfo = null;
//	        try {
//	            int num = 0;
//	            for (int i = 0; i < MSG_SIZE; i++) {
//	                quotationInfo = createQuotationInfo();
//	                record = new ProducerRecord<String, String>(TOPIC, null, 
//	                quotationInfo.getTradeTime(),quotationInfo.getStockCode(),
//	                quotationInfo.toString());
//	                producer.send(record);// 异步发送消息
//	                if (num++ % 10 == 0) {
//	                    Thread.sleep(2000L);// 休眠2s
//	                }
//	            }
//	        } catch (InterruptedException e) {
//	            LOG.error("Send message occurs exception", e);
//	        } finally {
//	            producer.close();
//	        }
//	    }
//}
