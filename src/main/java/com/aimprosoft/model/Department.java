package com.aimprosoft.model;


import com.aimprosoft.util.OvalValidDepName;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }


    @NotNull(message = " enter name")
    @MinLength(value = 3, message = " is shorter 3")
    @MaxLength(value = 21, message = " is bigger 21")
    @CheckWith(value = OvalValidDepName.class, message = " name exist")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (name != null ? name.equals(that.name) : that.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


}
/*
package com.aimprosoft.model;


import com.aimprosoft.util.OvalValidDepName;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department", catalog = "mydb")

public class Department implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    @NotNull(message = " enter name")
    @MinLength(value = 3, message = " is shorter 3")
    @MaxLength(value = 21, message = " is bigger 21")
    @CheckWith(value = OvalValidDepName.class, message = " name exist")
    private String name;

    public Department() {
    }


    public Department(long id) {
        this.id = id;
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (name != null ? name.equals(that.name) : that.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


}*/
