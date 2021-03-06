package pojos;
// Generated 30.5.2017 9:48:40 by Hibernate Tools 4.3.1



/**
 * SensorTbl generated by hbm2java
 */
public class SensorTbl  implements java.io.Serializable {


     private Integer idSensor;
     private String name;
     private String type;
    /**
     * Konstruktor triedy SensorTbl
     */ 
    public SensorTbl() {
    }
    /**
     *  Konstruktor triedy SensorTbl
     * @param name nazov senzoru
     * @param type typ senzoru
     */
    public SensorTbl(String name, String type) {
       this.name = name;
       this.type = type;
    }
   /**
    * geter
    * @return vrati ID senzoru
    */
    public Integer getIdSensor() {
        return this.idSensor;
    }
    /**
     * seter
     * @param idSensor ID senzoru
     */
    public void setIdSensor(Integer idSensor) {
        this.idSensor = idSensor;
    }
     /**
    * geter
    * @return vrati nazov senzoru
    */
    public String getName() {
        return this.name;
    }
    /**
     * seter
     * @param name nazov senzoru
     */
    public void setName(String name) {
        this.name = name;
    }
     /**
    * geter
    * @return vrati typ senzoru
    */
    public String getType() {
        return this.type;
    }
    /**
     * seter
     * @param type typ senzoru
     */
    public void setType(String type) {
        this.type = type;
    }


}


