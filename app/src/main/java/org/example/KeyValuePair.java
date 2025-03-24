package org.example;

public class KeyValuePair implements Comparable<KeyValuePair>
{
    private String key;
    private String value;

    public KeyValuePair(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getValue()
    {
        return this.value;
    }

    @Override
    public int compareTo(KeyValuePair other)
    {
        return this.key.compareTo(other.key);
    }
}