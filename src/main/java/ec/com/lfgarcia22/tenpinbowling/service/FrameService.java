package ec.com.lfgarcia22.tenpinbowling.service;

import ec.com.lfgarcia22.tenpinbowling.model.Frame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameService {

    public void addPinsKnockedDownToFrame(Integer pinFall, Frame frame) {
        int key = frame.getPinsKnockedDown().keySet().size() + 1;
        frame.getPinsKnockedDown().put(key, pinFall);
    }

    public void addPinFallsToFrame(String pinFall, Frame frame) {
        int key = frame.getPinFalls().keySet().size() + 1;
        int totalPinsKnockedDown = frame.getPinsKnockedDown().values().stream()
                .reduce(0, (subtotal, it) -> subtotal + it);
        if (key >= 2 && !pinFall.equals("X") && totalPinsKnockedDown % 10 == 0 && totalPinsKnockedDown > 0) {
            frame.getPinFalls().put(key, "/");
        } else {
            frame.getPinFalls().put(key, pinFall);
        }
    }

    public String getPinFallsFromFrames(List<Frame> frames) {
        String framesMapped = frames.stream().map(FrameService::mapPinFalls)
                .reduce("", (subtotal, value) -> subtotal.concat(value));
        return String.format("%s", framesMapped);
    }

    public String getFrameScoreFromFrames(List<Frame> frames) {
        String framesMapped = frames.stream().map(frame -> frame.getScore().toString())
                .reduce("", (subtotal, value) -> subtotal.concat("\t").concat("\t").concat(value));
        return String.format("%s", framesMapped.substring(2));
    }

    public static String mapPinFalls(Frame frame) {
        return frame.getPinFalls().values().stream()
                .reduce("", (subtotal, value) -> subtotal.concat("\t").concat(value));
    }

}
