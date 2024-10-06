package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {
    public static final class Post {
        private final String copyright;
        private final String date;
        private final String explanation;
        private final String hdurl;
        private final String media_type;
        private final String service_version;
        private final String title;
        private final String url;

        public Post(
                @JsonProperty("copyright") String copyright,
                @JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String hdurl,
                @JsonProperty("media_type") String media_type,
                @JsonProperty("service_version") String service_version,
                @JsonProperty("title") String title,
                @JsonProperty("url") String url) {
            this.copyright = copyright;
            this.date = date;
            this.explanation = explanation;
            this.hdurl = hdurl;
            this.media_type = media_type;
            this.service_version = service_version;
            this.title = title;
            this.url = url;
        }

        public String copyright() {
            return copyright;
        }

        public String date() {
            return date;
        }

        public String explanation() {
            return explanation;
        }

        public String hdurl() {
            return hdurl;
        }

        public String media_type() {
            return media_type;
        }

        public String service_version() {
            return service_version;
        }

        public String title() {
            return title;
        }

        public String url() {
            return url;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Post) obj;
            return Objects.equals(this.copyright, that.copyright) &&
                    Objects.equals(this.date, that.date) &&
                    Objects.equals(this.explanation, that.explanation) &&
                    Objects.equals(this.hdurl, that.hdurl) &&
                    Objects.equals(this.media_type, that.media_type) &&
                    Objects.equals(this.service_version, that.service_version) &&
                    Objects.equals(this.title, that.title) &&
                    Objects.equals(this.url, that.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(copyright, date, explanation, hdurl, media_type, service_version, title, url);
        }

        @Override
        public String toString() {
            return "Post[" +
                    "copyright=" + copyright + ", " +
                    "date=" + date + ", " +
                    "explanation=" + explanation + ", " +
                    "hdurl=" + hdurl + ", " +
                    "media_type=" + media_type + ", " +
                    "service_version=" + service_version + ", " +
                    "title=" + title + ", " +
                    "url=" + url + "]";
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Client client = new Client();

        Post post = client.sendRequestToGetClass("https://api.nasa.gov/planetary/apod?api_key=__");
        System.out.println(post);

        client.sendRequestToGetFile(post);
    }
}
