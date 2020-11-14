package net.notfab.lindsey.core.framework;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.notfab.lindsey.core.framework.i18n.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Utils {

    public static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(
                list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }

    public static String getTime(long millis, Member member, Translator i18n) {
        return getTime(millis, member.getUser(), i18n);
    }

    /**
     * @param millis - Time in milliseconds
     * @return String with time (1 day 3 hours 1 minute 10 seconds)
     */
    private static String getTime(Long millis, User user, Translator i18n) {
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        StringBuilder time = new StringBuilder();
        if (days > 0) {
            time.append(" ");
            if (days > 1) {
                time.append(i18n.get(user, "parts.days", String.valueOf(days)));
            } else {
                time.append(i18n.get(user, "parts.day", String.valueOf(days)));
            }
        }
        if (hours > 0) {
            time.append(" ");
            if (hours > 1) {
                time.append(i18n.get(user, "parts.hours", String.valueOf(hours)));
            } else {
                time.append(i18n.get(user, "parts.hour", String.valueOf(hours)));
            }
        }
        if (minutes > 0) {
            time.append(" ");
            if (minutes > 1) {
                time.append(i18n.get(user, "parts.minutes", String.valueOf(minutes)));
            } else {
                time.append(i18n.get(user, "parts.minute", String.valueOf(minutes)));
            }
        }
        if (seconds > 0) {
            time.append(" ");
            if (seconds > 1) {
                time.append(i18n.get(user, "parts.seconds", String.valueOf(seconds)));
            } else {
                time.append(i18n.get(user, "parts.second", String.valueOf(seconds)));
            }
        }
        return time.substring(1, time.length());
    }

    public static <T> Consumer<T> noop() {
        return t -> {};
    }

}