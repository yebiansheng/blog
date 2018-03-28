package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Message;

public interface MessageMapper {
    
    List<Message> getAllMsg();
    
    List<Message> getNoArtMsgByPage(int begin,int num);
    
    int getNoArtMsgNum();
    
    List<Message> getMsgByArtIdPage(int begin,int num,int artId);
    
    int getMsgNumByArtId(int artId);
    
    int insertMsg(Message msg);
}