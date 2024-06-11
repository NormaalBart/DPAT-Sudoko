package org.avans.sudoko.util;


public class TimeUtil {

    public static String formatSeconds(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        StringBuilder timeBuilder = new StringBuilder();
        if (hours > 0) {
            timeBuilder.append(String.format("%d:", hours));
        }
        if (minutes > 0 || hours > 0) {
            timeBuilder.append(String.format("%02d:", minutes));
        }
        timeBuilder.append(String.format("%02d", seconds));

        return timeBuilder.toString();
    }
}
