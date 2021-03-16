package ccf.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int salesmanId;

    private String username;

    private String password;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(int salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
