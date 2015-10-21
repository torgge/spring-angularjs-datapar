### For mapping a composite key, there are a couple of options. You can

Define a separate @Embeddable object with the PK fields and use it as @EmbeddedId in your @Entity class

@Embeddable
public class MyCompositePK { 
    @Column
    private String fieldA;
    @Column
    private String fieldB;
}
@Entity 
public class MyBean { 
    @EmbeddedId
    private MyCompositePK id;
    @Column
    private String fieldC;
}
Define a non-mapped POJO with the PK fields and use it as @IdClass in the @Entity.

@Entity
@IdClass(value=ClassAB.ClassABId.class)
public class ClassAB implements Serializable {
    private String idA;
    private String idB;

    @Id
    @Column(name="ID_A")
    public String getIdA(){ return idA; }
    public void setIdA(String idA){ this.idA = idA; }

    @Id
    @Column(name="ID_B")
    public String getIdB(){ return idB; }
    public void setIdB(String idB){ this.idB = idB; }

    static class ClassABId implements Serializable {
        private String idA;
        private String idB;

        public String getIdA(){ return idA; }
        public void setIdA(String idA){ this.idA = idA; }

        public String getIdB(){ return idB; }
        public void setIdB(String idB){ this.idB = idB; }

        // implement equals(), hashcode()
    }
}
In this example ClassABId is a static inner class just for convenience.

These options are also explained in Pascal Thivent's excellent answer to this question: How to map a composite key with Hibernate?.

This related question discusses differences between these approaches: Which anotation should I use: @IdClass or @EmbeddedId. Notice the fields' declaration gets duplicated with the @IdClass approach.

Anyhow, I don't think there's an alternative to creating two classes. That's why I asked this question : Mapping a class that consists only of a composite PK without @IdClass or @EmbeddedId. It seems there's an hibernate-specific feature for this.

As a side note, if you've got control over the DB structure, you might also consider avoiding composite keys. There are some reasons to do so.
