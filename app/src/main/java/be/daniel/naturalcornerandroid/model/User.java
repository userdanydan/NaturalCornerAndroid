package be.daniel.naturalcornerandroid.model;

/**
 * Created by ivymike on 08/09/16.
 */
public class User {
    private int id;
    private String name, email, nickname, password;

    public User() {
    }

    public User(String name, String email, String nickname, String password) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null)
            return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (getNickname() != null ? !getNickname().equals(user.getNickname()) : user.getNickname() != null)
            return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
