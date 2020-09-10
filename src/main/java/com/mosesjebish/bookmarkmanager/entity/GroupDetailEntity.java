package com.mosesjebish.bookmarkmanager.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "t_group_details")
@Getter
@Setter
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class GroupDetailEntity {

    @Id
    @GeneratedValue(generator = "group_detail_seq_id")
    @SequenceGenerator(name = "group_detail_seq_id", sequenceName = "group_detail_seq_id", allocationSize = 1)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "created_by")
    private String createdBy;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb" , name = "admin_list")
    private String adminList;

}
