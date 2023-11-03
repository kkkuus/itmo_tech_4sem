package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OwnerId")
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "date")
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Cat> cats;

    public Owner(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Owner() {}
}
