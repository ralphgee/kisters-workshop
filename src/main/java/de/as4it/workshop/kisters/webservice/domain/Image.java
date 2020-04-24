package de.as4it.workshop.kisters.webservice.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.net.URL;
import java.time.LocalDate;

/**
 * Created by andy on 14.01.16.
 */
@JsonAutoDetect
@Entity
@Getter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty
    @NotBlank
    @Size(min=4,max=64)
    private String name;

    @JsonProperty
    @NotNull
    private URL location;

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private LocalDate publishedAt;

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private LocalDate lastModified;
    
    public Image(String name, URL location,LocalDate pub, LocalDate lastModified) {
        this.name = name;
        this.location = location;

        this.publishedAt = pub;
        this.lastModified = lastModified;

    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public Image calculate()
    {
        return this;
    }
    
}
