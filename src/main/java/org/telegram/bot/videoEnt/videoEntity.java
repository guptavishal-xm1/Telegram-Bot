package org.telegram.bot.videoEnt;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class videoEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
   private String f_id;

    public videoEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }
}
