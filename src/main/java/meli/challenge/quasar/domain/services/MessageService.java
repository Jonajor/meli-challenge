package meli.challenge.quasar.domain.services;

import meli.challenge.quasar.domain.dtos.request.SatelliteDto;
import meli.challenge.quasar.domain.dtos.request.SatelliteData;
import meli.challenge.quasar.domain.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    public String getMessage(SatelliteData satelliteData){
        if (satelliteData.getSatelliteDtos().size() <= 2){
            throw new UnprocessableEntityException();
        }

        var messageList = satelliteData.getSatelliteDtos()
                .stream()
                .map(SatelliteDto::getMessage)
                .filter(m -> m != null && !m.isEmpty())
                .collect(Collectors.toList());

        if (messageList.stream().allMatch(m -> m.isEmpty())){
            throw new UnprocessableEntityException();
        }

        return filterWords(messageList);
    }

    public String filterWords(List<List<String>> messageList) {
        List<String> wordList = new ArrayList<>();
        for (List<String> msg: messageList){
            wordList = Stream.concat(wordList.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        wordList.remove("");
        removeGap(messageList, wordList.size());
        return completeMessage(messageList);
    }

    private void removeGap(List<List<String>> msgList, int gapSize){

        int s = 0;
        for(int i = 0; i < msgList.size(); i++){
            s = msgList.get(i).size();
            msgList.set(i, msgList.get(i).subList(s-gapSize, s));
        }

    }

    public String completeMessage(List<List<String>> msgList){

        String phrase = "";
        for(List<String> m : msgList){

            if(m.size()>0 && !m.get(0).equals("")){
                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
                msgList.stream().forEach( s -> s.remove(0));
                return  phrase + completeMessage(msgList);
            }
        }
        return "";
    }

}
