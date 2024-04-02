package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        JToggleButton hourFormatButton;
        JToggleButton timeZoneButton;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(350, 220);
        this.setResizable(false);

//        Sets the formats for the time, day, and date
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat=new SimpleDateFormat("EEEE");
        dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");

//        Sets the font and color of the time
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 50));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);

//        Sets the font and size of the day
        dayLabel=new JLabel();
        dayLabel.setFont(new Font("Ink Free",Font.BOLD,25));

//        Sets the font and size of the date
        dateLabel=new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.BOLD,25));

//        Button for switching time zones
        timeZoneButton = new JToggleButton("local/GMT");
        timeZoneButton.setFont(new Font("Ink Free", Font.BOLD, 10));
        ItemListener itemListener1 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    time = timeFormat.format(Calendar.getInstance().getTime());
                    timeLabel.setText(time);
                }
            }
        };

//        Button for switching hour format
        hourFormatButton = new JToggleButton("12/24 hr");
        hourFormatButton.setFont(new Font("Ink Free", Font.BOLD, 10));
        ItemListener itemListener2 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    timeFormat = new SimpleDateFormat("HH:mm");
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                }
            }
        };

        timeZoneButton.addItemListener(itemListener1);
        hourFormatButton.addItemListener(itemListener2);
        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.add(hourFormatButton);
        this.add(timeZoneButton);
        this.setVisible(true);

//        Runs the setTimer method
        setTimer();
    }

    public void setTimer() {
//        Thread will ensure the setTimer method runs 120 times (2 mins)
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 120; i++) {

                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        new SimpleClock();
    }
}
