package com.mosesjebish.bookmarkmanager.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "t_card_details")
@Getter
@Setter
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class CardDetailEntity {
    @Id
    @GeneratedValue(generator = "card_detail_seq_id")
    @SequenceGenerator(name = "card_detail_seq_id", sequenceName = "card_detail_seq_id", allocationSize = 1)
    private Long id;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "tribe")
    private String tribe;

    @Column(name = "application")
    private String application;

    @Column(name = "platform")
    private String platform;

    @Column(name = "description")
    private String description;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "group_details")
    private String groupDetails;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "user_details")
    private String userDetails;

    @Column(name = "approved")
    private Boolean approved;

}
