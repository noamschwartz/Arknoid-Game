package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

/**
 * BlockDefinitionReader class. reads the definitions of the blocks and return factory.
 */
public class BlockDefinitionReader {

    /**
     * this reads the blocks definitions and returns a new factory.
     *
     * @param reader of the file.
     * @return all the blocks to create.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        TreeMap<String, TreeMap<String, String>> symbol = new TreeMap<String, TreeMap<String, String>>();
        TreeMap<String, Integer> spaces = new TreeMap<String, Integer>();
        TreeMap<String, String> defaults = new TreeMap<String, String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.contains("#")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                if (line.contains("default")) {
                    String[] lineSplit = line.split(" ");
                    for (String s : lineSplit) {
                        if (s.contains(":")) {
                            String[] split = s.split(":");
                            defaults.put(split[0], split[1]);
                        }
                    }
                }
                if (line.contains("bdef")) {
                    TreeMap<String, String> inValueMap = new TreeMap<String, String>();
                    line = line.replace("bdef symbol:", "");
                    String[] split = line.split(" ");
                    String key = split[0];
                    for (String s : split) {
                        if (s.contains(":")) {
                            String[] mapSplit = s.split(":");
                            inValueMap.put(mapSplit[0], mapSplit[1]);
                        }
                    }
                    symbol.put(key, inValueMap);
                }
                if (line.contains("sdef")) {
                    line = line.replace("sdef symbol:", "");
                    String[] split = line.split(" ");
                    String key = split[0];
                    Integer value = Integer.parseInt(split[1].replace("width:", ""));
                    spaces.put(key, value);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> blockKeys = symbol.keySet();
        for (String key : blockKeys) {
            TreeMap<String, String> map = symbol.get(key);
            Set<String> inDefaultsKeys = defaults.keySet();
            for (String keys : inDefaultsKeys) {
                if (!map.containsKey(keys)) {
                    map.put(keys, defaults.get(keys));
                }
            }
            symbol.replace(key, map, map);
        }
        try {
            Set<String> keys = symbol.keySet();
            TreeMap<String, BlockCreator> mapToRet = new TreeMap<String, BlockCreator>();
            for (String key : keys) {
                TreeMap<String, String> valueMap = symbol.get(key);
                BlockCreator bc = new BlockFactoryByMap(key, valueMap);
                mapToRet.put(key, bc);
            }
            return new BlocksFromSymbolsFactory(spaces, mapToRet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

}