package br.com.kirgh.app.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "parent")
public class UserRelation {
    private String type;
    private User parent;
}
