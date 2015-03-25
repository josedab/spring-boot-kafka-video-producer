package com.utiltube.kafka.video.model;


public class Video {

    private static final int IMPOSSIBLE_ID = -1;

    private int id = IMPOSSIBLE_ID;
    private String identifier;
    private String provider;
    private String title;
    private String description;

    private Video(Builder builder) {
        this.id = builder.id;
        this.identifier = builder.identifier;
        this.provider = builder.provider;
        this.title = builder.title;
        this.description = builder.description;
    }
    
    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getProvider() {
        return provider;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        
        private int id = IMPOSSIBLE_ID;
        private final String identifier;
        private final String provider;
        private final String title;
        private String description;

        public Builder(String identifier, String provider, String title) {
            this.identifier = identifier;
            this.provider = provider;
            this.title = title;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }
        
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Video build() {
            return new Video(this);
        }
    }
}
