package Event;

import charactor.Item;
import charactor.Wave;
import charactor.Nutcha;

public class Event {
    public static boolean checkHit(Nutcha nc, Wave wave, int NCSize, int waveHeight) {
        if (nc.x + nc.NCSize > wave.x && nc.x < wave.x) {
            if (nc.y + nc.NCSize >= wave.y - waveHeight) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkHit(Nutcha nc, Wave wave) {
        return checkHit(nc, wave, nc.NCSize, wave.height);
    }

    public static boolean checkHit(Nutcha nc, Item item) {
        if (nc.x + nc.NCSize > item.x && nc.x < item.x + item.width) {
            if (nc.y + nc.NCSize >= item.y && nc.y <= item.y + item.height) {
                return true;
            }
        }
        return false;
    }
}