//package com.cn.demo.test01.utils;
//
//import java.util.Properties;
//
//import org.apache.kafka.common.security.JaasUtils;
//import org.apache.zookeeper.ZKUtil;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
//
//import kafka.admin.AdminUtils;
//import kafka.admin.BrokerMetadata;
//import kafka.server.ConfigType;
//import kafka.utils.ZkUtils;
//import scala.collection.Map;
//import scala.collection.Seq;
//
//public class KafkaUtil {
//	
//	private  final static String ZK_CONNECT = "106.12.215.254:2181";
//	private  final static Integer TIME_OUT = 3000;
//	private final static Integer SESSION_TIME_OUT = 3000;
//	/**
//	 *  创建topic
//	 * @param topic 话题
//	 * @param partition 分区
//	 * @param repilca 副本
//	 * @param properties 配置
//	 */
//	public static void createTopic(String topic,int partition,int repilca,Properties properties){
//		ZkUtils zkUtils = null;
//		try {
//		
//			zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIME_OUT, TIME_OUT, JaasUtils.isZkSecurityEnabled());
//			//如果topic不存在则创建
//			if(!AdminUtils.topicExists(zkUtils, topic)) {
//				AdminUtils.createTopic(zkUtils, topic, partition, repilca, properties, AdminUtils.createTopic$default$6());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			zkUtils.close();
//		}
//	
//	
//	}
//	
//	
//	/**
//	 * 添加分区
//	 * 假设一个主题名为“partition-api-foo”的主题，该主题有一个分区、两个副本，
//	 * 当前分配方案为{"version":1,"partitions":{"0":[3,1] } }。
//	 * @param topic
//	 * @param partition
//	 * @param plan
//	 */
//	public static void addPartition(String topic,int partition,String plan ) {
//		ZkUtils zkUtils = null;
//		try {
//			 zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIME_OUT, TIME_OUT, JaasUtils.isZkSecurityEnabled());
//			 AdminUtils.addPartitions(zkUtils, topic, partition,plan, true,
//			 AdminUtils. addPartitions$default$6());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			zkUtils.close();
//		}
//		
//	}
//	
//	/**
//	 * 修改topic配置
//	 * @param topic
//	 * @param properties
//	 */
//	public static void modifyTopicConfig(String topic, Properties properties) {
//	    ZkUtils zkUtils = null;    
//	    try {
//	        // 实例化 ZkUtils
//	        zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIME_OUT,TIME_OUT, 
//	        JaasUtils.isZkSecurityEnabled());
//	        // 首先获取当前已有的配置，这里是查询主题级别的配置，因此指定配置类型为 Topic
//	        Properties curProp = AdminUtils.fetchEntityConfig(zkUtils, 
//	        ConfigType.Topic(),topic);// 添加新修改的配置
//	        curProp.putAll(properties);
//	        AdminUtils.changeTopicConfig(zkUtils, topic, curProp);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        zkUtils.close();
//	    }
//	}
//	
//	
//	/**
//	 * 修改分区副本
//	 * @param topic
//	 * @param parttion
//	 * @param replica
//	 */
//	public static void modifyPartitionReplica(String topic,int parttion,int replica) {
//		
//		ZkUtils zkUtils = null;
//		try {
//			zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIME_OUT, TIME_OUT, JaasUtils.isZkSecurityEnabled());
//			//获取代理元数据信息
//			Seq<BrokerMetadata> brokerMetaData = AdminUtils.getBrokerMetadatas(zkUtils, AdminUtils.getBrokerMetadatas$default$2(), 
//					AdminUtils.getBrokerMetadatas$default$3());
//			Map<Object,Seq<Object>> replicaAssign =  AdminUtils.assignReplicasToBrokers(brokerMetaData, parttion, replica,
//					AdminUtils.assignReplicasToBrokers$default$4(), AdminUtils.assignReplicasToBrokers$default$5());
//			//修改分区副本分配方案
//			AdminUtils.createOrUpdateTopicPartitionAssignmentPathInZK(zkUtils, topic, replicaAssign, null, true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			zkUtils.close();
//		}
//	}
//	
//	/**
//	 * 删除 topic
//	 * @param topic
//	 */
//	public static void removeTopic(String topic) {
//		ZkUtils zkUtils = null;
//		try {
//			zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIME_OUT, TIME_OUT, JaasUtils.isZkSecurityEnabled());
//			//获取代理元数据信息
//			AdminUtils.deleteTopic(zkUtils, topic);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			zkUtils.close();
//		}
//	}
//}
//
