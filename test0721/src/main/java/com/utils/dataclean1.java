package com.utils;

import java.io.*;
import java.util.regex.Pattern;

public class dataclean1 {
    public static void main(String[] args) {
        clean1();
        // boolean dsgs = isInteger("12");
        // System.out.println(dsgs);
    }

    public static void clean1() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:/test.log"))));
            //BufferedWriter bw = new BufferedWriter(new FileWriter("E:/data.log"));
            String line = bf.readLine();
            int i = 0;
            while (line != null) {
                String[] split = line.split(" ");
                if (split[11].contains("video") || split[11].contains("article")) {

                    String[] split1 = split[11].split("/");
                    if (null != split1[3]) {
                        if (split1[3].equals("article") || split1[3].equals("video")) {

                            split1[4] = split1[4].substring(0, split1[4].length() - 1);

                            if (split1[4].contains("\\?")) {
                                split1[4] = split1[4].substring(0, split1[4].indexOf("?") - 1);
                            }
                            boolean flag = isInteger(split1[4]);
                            if (flag == true) {

                                String s = split[0] + " " + split[3] + " " + split[4] + " " + split[9] + " " + split[11];
                                System.out.println("第" + (i + 1) + "行:    " + s + "      " + flag+"           "+split1[4]);
                                i++;
                            }
                        }
                    }

                }
                line = bf.readLine();

            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
