package edu.school21.springboot42.services;

import edu.school21.springboot42.models.LogInfo;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.repositories.LogInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoService {

    @Autowired
    LogInfoRepository logInfoRepository;

    public void saveLogInfo(LogInfo logInfo) {
        logInfoRepository.saveAndFlush(logInfo);
    }

    public List<LogInfo> findLogInfoByUser(User user) {
        return logInfoRepository.findByUser(user);
    }

}
