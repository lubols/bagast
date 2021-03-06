package pojos;
// Generated 30.5.2017 9:48:40 by Hibernate Tools 4.3.1

/**
 * UserTbl generated by hbm2java
 */
public class UserTbl implements java.io.Serializable {

    private Integer idUser;
    private String name;
    private String gender;
    private String address;
    private String heslo;

    /**
     * Konstruktor triedy UserTbl
     */
    public UserTbl() {
    }
    /**
     * Konstruktor triedy UserTbl
     * @param name meno uzivatel
     * @param gender pohlavie uzivatela
     * @param address emailova adresa uzivatela
     * @param heslo heslo uzivatela
     */
    public UserTbl(String name, String gender, String address, String heslo) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.heslo = heslo;
    }
    /**
     * geter
     * @return vrati ID uzivatela
     */
    public Integer getIdUser() {
        return this.idUser;
    }
    /**
     * seter
     * @param idUser ID uzivatela
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    /**
     * geter
     * @return vrati meno uzivatela
     */
    public String getName() {
        return this.name;
    }
    /**
     * seter
     * @param name meno uzivatela
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * geter
     * @return vrati pohlavie uzivatela
     */
    public String getGender() {
        return this.gender;
    }
    /**
     * seter
     * @param gender pohlavie uzivatela
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * geter
     * @return vrati emailovu adresu uzivatela
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * seter
     * @param address adresa uzivatela
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * geter
     * @return vrati heslo uzivatela
     */
    public String getHeslo() {
        return this.heslo;
    }
    /**
     * seter
     * @param heslo  heslo uzivatela
     */
    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

}
