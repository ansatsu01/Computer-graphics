import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

public class App extends JFrame {

    int red = 0, green = 0, blue = 0;
    int cyan = 0, magenta = 0, yellow = 0, black = 100;
    int hue = 0, saturation = 0, value = 0;
    JSpinner rText, gText, bText, cyanText, magentaText, yellowText, blackText, hueText, saturationText, valueText;
    JSlider rSlider, gSlider, bSlider, cyanSlider, magentaSlider, yellowSlider, blackSlider, hueSlider, saturationSlider, valueSlider;
    boolean rb = true, gb = true, bb = true, cb = true, mb = true, yb = true, blkb = true, hueb = true, saturb = true, valueb;
    Palette palette;
    Current current;

    public App(String title) {
        super(title);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        palette = new Palette();

        JLabel rgbLabel = new JLabel("RGB");
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 0;
        add(rgbLabel, c);
        JLabel rLabel = new JLabel("red");
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 1;
        add(rLabel, c);
        rText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 1;
        rText.setSize(100, 30);
        add(rText, c);
        rSlider = new JSlider(0, 255, 0);
        c.gridx = 2;
        c.gridy = 1;
        add(rSlider, c);

        JLabel gLabel = new JLabel("green");
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 2;
        add(gLabel, c);
        gText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 2;
        gText.setSize(100, 30);
        add(gText, c);
        gSlider = new JSlider(0, 255, 0);
        c.gridx = 2;
        c.gridy = 2;
        add(gSlider, c);

        JLabel bLabel = new JLabel("blue");
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 3;
        add(bLabel, c);
        bText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 3;
        bText.setSize(100, 30);
        add(bText, c);
        bSlider = new JSlider(0, 255, 0);
        c.gridx = 2;
        c.gridy = 3;
        add(bSlider, c);

        JLabel cmykLabel = new JLabel("CMYK");
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 4;
        add(cmykLabel, c);
        JLabel cyanLabel = new JLabel("cyan");
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 5;
        add(cyanLabel, c);
        cyanText = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 5;
        cyanText.setSize(100, 30);
        add(cyanText, c);
        cyanSlider = new JSlider(0, 100, 0);
        c.gridx = 2;
        c.gridy = 5;
        add(cyanSlider, c);

        JLabel magentaLabel = new JLabel("magenta");
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 6;
        add(magentaLabel, c);
        magentaText = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 6;
        magentaText.setSize(100, 30);
        add(magentaText, c);
        magentaSlider = new JSlider(0, 100, 0);
        c.gridx = 2;
        c.gridy = 6;
        add(magentaSlider, c);

        JLabel yellowLabel = new JLabel("yellow");
        c.gridx = 0;
        c.gridy = 7;
        add(yellowLabel, c);
        yellowText = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        c.gridx = 1;
        c.gridy = 7;
        yellowText.setSize(100, 30);
        add(yellowText, c);
        yellowSlider = new JSlider(0, 100, 0);
        c.gridx = 2;
        c.gridy = 7;
        add(yellowSlider, c);

        JLabel blackLabel = new JLabel("black");
        c.gridx = 0;
        c.gridy = 8;
        add(blackLabel, c);
        blackText = new JSpinner(new SpinnerNumberModel(100, 0, 100, 1));
        c.gridx = 1;
        c.gridy = 8;
        blackText.setSize(100, 30);
        add(blackText, c);
        blackSlider = new JSlider(0, 100, 100);
        c.gridx = 2;
        c.gridy = 8;
        add(blackSlider, c);

        JLabel hsvLabel = new JLabel("HSV");
        c.gridx = 1;
        c.gridy = 9;
        add(hsvLabel, c);
        JLabel hueLabel = new JLabel("hue");
        c.gridx = 0;
        c.gridy = 10;
        add(hueLabel, c);
        hueText = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));
        c.gridx = 1;
        c.gridy = 10;
        hueText.setSize(100, 30);
        add(hueText, c);
        hueSlider = new JSlider(0, 360, 0);
        c.gridx = 2;
        c.gridy = 10;
        add(hueSlider, c);

        JLabel saturationLabel = new JLabel("saturation");
        c.gridx = 0;
        c.gridy = 11;
        add(saturationLabel, c);
        saturationText = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        c.gridx = 1;
        c.gridy = 11;
        saturationText.setSize(100, 30);
        add(saturationText, c);
        saturationSlider = new JSlider(0, 100, 0);
        c.gridx = 2;
        c.gridy = 11;
        add(saturationSlider, c);

        JLabel valueLabel = new JLabel("value");
        c.gridx = 0;
        c.gridy = 12;
        add(valueLabel, c);
        valueText = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        c.gridx = 1;
        c.gridy = 12;
        valueText.setSize(100, 30);
        add(valueText, c);
        valueSlider = new JSlider(0, 100, 0);
        c.gridx = 2;
        c.gridy = 12;
        add(valueSlider, c);

        c.gridx = 2;
        c.gridy = 13;
        add(palette, c);

        current = new Current();
        c.gridx = 0;
        c.gridy = 13;
        add(current, c);

        rText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rb) {
                    red = (int) rText.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    rb = true;
                }
            }
        });
        gText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (gb) {
                    green = (int) gText.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    gb = true;
                }
            }
        });
        bText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (bb) {
                    blue = (int) bText.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    bb = true;
                }
            }
        });

        cyanText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (cb) {
                    cyan = (int) cyanText.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    cb = true;
                }
            }
        });
        magentaText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mb) {
                    magenta = (int) magentaText.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    mb = true;
                }
            }
        });
        yellowText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (yb) {
                    yellow = (int) yellowText.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    yb = true;
                }
            }
        });
        blackText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (blkb) {
                    black = (int) blackText.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    blkb = true;
                }
            }
        });

        hueText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (hueb) {
                    hue = (int) hueText.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    hueb = true;
                }
            }
        });
        saturationText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (saturb) {
                    saturation = (int) saturationText.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    saturb = true;
                }
            }
        });
        valueText.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (valueb) {
                    value = (int) valueText.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    valueb = true;
                }
            }
        });

        rSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rb) {
                    red = (int) rSlider.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    rb = true;
                }
            }
        });
        gSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (gb) {
                    green = (int) gSlider.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    gb = true;
                }
            }
        });
        bSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (bb) {
                    blue = (int) bSlider.getValue();
                    rgbChange();
                    current.upd();
                } else {
                    bb = true;
                }
            }
        });

        cyanSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (cb) {
                    cyan = (int) cyanSlider.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    cb = true;
                }
            }
        });
        magentaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mb) {
                    magenta = (int) magentaSlider.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    mb = true;
                }
            }
        });
        yellowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (yb) {
                    yellow = (int) yellowSlider.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    yb = true;
                }
            }
        });
        blackSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (blkb) {
                    black = (int) blackSlider.getValue();
                    cmykChange();
                    current.upd();
                } else {
                    blkb = true;
                }
            }
        });

        hueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (hueb) {
                    hue = (int) hueSlider.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    hueb = true;
                }
            }
        });
        saturationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (saturb) {
                    saturation = (int) saturationSlider.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    saturb = true;
                }
            }
        });
        valueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (valueb) {
                    value = (int) valueSlider.getValue();
                    hsvChange();
                    current.upd();
                } else {
                    valueb = true;
                }
            }
        });
    }

    private void cmykChange() {
        red = (int) (255 * (1 - (float) cyan / 100) * (1 - (float) black / 100));
        green = (int) (255 * (1 - (float) magenta / 100) * (1 - (float) black / 100));
        blue = (int) (255 * (1 - (float) yellow / 100) * (1 - (float) black / 100));

        float mx = max((float) red / 255, max((float) green / 255, (float) blue / 255));
        float mn = min((float) red / 255, min((float) green / 255, (float) blue / 255));

        value = (int) (100 * mx);
        float C = mx - mn;

        if (mx == 0) {
            saturation = 0;
        } else {
            saturation = (int) ((1 - mn / mx) * 100);
        }

        if (mx != mn) {
            if (mx * 255 == red) {
                if (mx * 255 == red) {
                    if (green > blue) {
                        hue = (int) (60 * ((float) green / 255 - (float) blue / 255) / C);
                    } else {
                        hue = (int) (60 * ((float) green / 255 - (float) blue / 255) / C) + 360;
                    }
                }
            }
            if (mx * 255 == green) {
                hue = (int) (60 * ((float) blue / 255 - (float) red / 255) / (mx - mn) + 120);
            }
            if (mx * 255 == blue) {
                hue = (int) (60 * ((float) red / 255 - (float) green / 255) / (mx - mn) + 120);
            }
        }

        rb = false;
        gb = false;
        bb = false;
        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        current.upd();
        palette.upd();

        rText.setValue(red);
        gText.setValue(green);
        bText.setValue(blue);
        blackText.setValue(black);
        cyanText.setValue(cyan);
        magentaText.setValue(magenta);
        yellowText.setValue(yellow);
        hueText.setValue(hue);
        saturationText.setValue(saturation);
        valueText.setValue(value);

        rb = false;
        gb = false;
        bb = false;
        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        rSlider.setValue(red);
        gSlider.setValue(green);
        bSlider.setValue(blue);
        blackSlider.setValue(black);
        cyanSlider.setValue(cyan);
        magentaSlider.setValue(magenta);
        yellowSlider.setValue(yellow);
        hueSlider.setValue(hue);
        saturationSlider.setValue(saturation);
        valueSlider.setValue(value);
    }

    private void rgbChange() {
        black = (int) (100 * (1 - max((float) red / 255, max((float) green / 255, (float) blue / 255))));
        cyan = (int) (100 * (1 - (float) red / 255 - (float) black / 100) / (1 - (float) black / 100));
        magenta = (int) (100 * (1 - (float) green / 255 - (float) black / 100) / (1 - (float) black / 100));
        yellow = (int) (100 * (1 - (float) blue / 255 - (float) black / 100) / (1 - (float) black / 100));

        float mx = max((float) red / 255, max((float) green / 255, (float) blue / 255));
        float mn = min((float) red / 255, min((float) green / 255, (float) blue / 255));
        float C = mx - mn;

        value = (int) (100 * mx);

        if (mx == 0) {
            saturation = 0;
        } else {
            saturation = (int) ((1 - mn / mx) * 100);
        }

        if (mx != mn) {
            if (mx * 255 == red) {
                if (green > blue) {
                    hue = (int) (60 * ((float) green / 255 - (float) blue / 255) / C);
                } else {
                    hue = (int) (60 * ((float) green / 255 - (float) blue / 255) / C) + 360;
                }
            }
            if (mx * 255 == green) {
                hue = (int) (60 * ((float) blue / 255 - (float) red / 255) / C + 120);
            }
            if (mx * 255 == blue) {
                hue = (int) (60 * ((float) red / 255 - (float) green / 255) / C + 120);
            }
        }

        current.upd();
        palette.upd();

        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        rb = false;
        gb = false;
        bb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        blackText.setValue(black);
        cyanText.setValue(cyan);
        magentaText.setValue(magenta);
        yellowText.setValue(yellow);
        rText.setValue(red);
        gText.setValue(green);
        bText.setValue(blue);
        hueText.setValue(hue);
        saturationText.setValue(saturation);
        valueText.setValue(value);

        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        rb = false;
        gb = false;
        bb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        blackSlider.setValue(black);
        cyanSlider.setValue(cyan);
        magentaSlider.setValue(magenta);
        yellowSlider.setValue(yellow);
        rSlider.setValue(red);
        gSlider.setValue(green);
        bSlider.setValue(blue);
        hueSlider.setValue(hue);
        saturationSlider.setValue(saturation);
        valueSlider.setValue(value);
    }

    private void hsvChange() {

        float C = ((float) value / 100 * (float) saturation / 100);
        float X = C * (1 - abs(((float) hue / 60) % 2 - 1));

        float red1, blue1, green1;

        if (hue < 60) {
            red1 = C;
            green1 = X;
            blue1 = 0;
        } else if (hue < 120) {
            red1 = X;
            green1 = C;
            blue1 = 0;
        } else if (hue < 180) {
            red1 = 0;
            green1 = C;
            blue1 = X;
        } else if (hue < 240) {
            red1 = 0;
            green1 = X;
            blue1 = C;
        } else if (hue < 300) {
            red1 = X;
            green1 = 0;
            blue1 = C;
        } else {
            red1 = C;
            green1 = 0;
            blue1 = X;
        }

        float m = (float) value / 100 - C;

        red = (int) ((red1 + m) * 255);
        green = (int) ((green1 + m) * 255);
        blue = (int) ((blue1 + m) * 255);

        black = (1 - max(red / 255, max(green / 255, blue / 255)));
        cyan = (int) (100 * (1 - (float) red / 255 - (float) black / 255) / (1 - (float) black / 255));
        magenta = (int) (100 * (1 - (float) green / 255 - (float) black / 255) / (1 - (float) black / 255));
        yellow = (int) (100 * (1 - (float) blue / 255 - (float) black / 255) / (1 - (float) black / 255));

        current.upd();
        palette.upd();

        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        rb = false;
        gb = false;
        bb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        blackText.setValue(black);
        cyanText.setValue(cyan);
        magentaText.setValue(magenta);
        yellowText.setValue(yellow);
        rText.setValue(red);
        gText.setValue(green);
        bText.setValue(blue);
        hueText.setValue(hue);
        saturationText.setValue(saturation);
        valueText.setValue(value);

        blkb = false;
        cb = false;
        mb = false;
        yb = false;
        rb = false;
        gb = false;
        bb = false;
        hueb = false;
        saturb = false;
        valueb = false;

        blackSlider.setValue(black);
        cyanSlider.setValue(cyan);
        magentaSlider.setValue(magenta);
        yellowSlider.setValue(yellow);
        rSlider.setValue(red);
        gSlider.setValue(green);
        bSlider.setValue(blue);
        hueSlider.setValue(hue);
        saturationSlider.setValue(saturation);
        valueSlider.setValue(value);
    }

    public static void main(String[] args) {
        JFrame mainWindow = new App("Series");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        mainWindow.setVisible(true);
    }

    class Palette extends JComponent {

        private int width = 256;
        private int height = 256;
        public BufferedImage image;
        public Graphics2D graphics;

        public Palette() {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            graphics = image.createGraphics();
            for (int i = 0; i <= 255; i++) {
                for (int j = 0; j <= 255; j++) {
                    graphics.setColor(new Color(i, j, blue));
                    graphics.fillRect(i, j, 1, 1);
                }
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    red = e.getX() % 256;
                    green = e.getY() % 256;
                    graphics.setColor(new Color(e.getX(), e.getY(), 0));
                    graphics.fillRect(0, 0, 256, 256);
                    rgbChange();
                    cmykChange();
                }
            });
        }

        public void upd() {
            for (int i = 0; i <= 255; i++) {
                for (int j = 0; j <= 255; j++) {
                    graphics.setColor(new Color(i, j, blue));
                    graphics.fillRect(i, j, 1, 1);
                }
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(image.getWidth(), image.getHeight());
        }

    }

    class Current extends JComponent {

        private int width = 50;
        private int height = 50;
        public BufferedImage image;
        public Graphics2D graphics;

        public Current() {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            graphics = image.createGraphics();

            graphics.setColor(new Color(red, green, blue));
            graphics.fillRect(0, 0, width, height);
        }

        public void upd() {
            graphics.setColor(new Color(red, green, blue));
            graphics.fillRect(0, 0, width, height);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(image.getWidth(), image.getHeight());
        }

    }
}
