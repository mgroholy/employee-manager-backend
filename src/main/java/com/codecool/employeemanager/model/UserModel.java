package com.codecool.employeemanager.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @CollectionTable(joinColumns = {@JoinColumn(foreignKey = @ForeignKey(
            foreignKeyDefinition = "FOREIGN KEY (user_model_id) references public.user_model (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE"))})
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ClearanceLevel> levels = new ArrayList<>();

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

}
