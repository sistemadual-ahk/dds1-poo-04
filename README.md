#  Cuarto módulo - Paradigma Orientado a Objetos
**Índice**
- [Paradigma orientado a Objetos](#paradigma-orientado-a-objetos)
    - [Colecciones](#colecciones)
    - [Método lookup](#metodo-lookup)
    - [Garbage collector](#garbage-collector)
- [Extendiendo el dominio](#extendiendo-el-dominio)
    - [Décima primera iteración](#décima-primera-iteración)
        - Dominio
        - Diseño en objetos
        - Implementación
        - Tests
            - En Java

### Paradigma Orientado a Objetos
#### Colecciones
Una colección es un objeto que dentro suyo contiene referencias a otros objetos. Su responsabilidad es contener y manejar un grupo de objetos.
Las colecciones, por lo general, pueden contener objetos de distintas
clases siempre y cuando compartan al menos una interface.
Las colecciones varían en las tareas que pueden realizar y en los mensajes que saben responder, por lo tanto, hay distintas clases de colecciones, aunque hay algunas tareas que las puede realizar cualquier colección.

Hay colecciones que tienen organizados sus elementos. Esta organización
puede consistir en un índice numérico que permite acceder a una posición
determinada de la colección, una clave de acceso más compleja para búsqueda
directa o tener algún criterio de orden interno.
También tienen definidos un abanico amplio de métodos que permiten
manipular su contenido evitando el uso de estructuras de control adicionales.

En Java existen, principalmente, dos tipos de colecciones: **Set** y **List**.
##### Set Collection
Esta interface no agrega nuevos mensajes que deberían cumplir las colecciones que la implementen, pero sirve para indicar que las implementaciones de dicha interface **no van a tener elementos repetidos** .
La decisión que toma la colección de si va a poner los elementos en la lista la toma en base a la *equivalencia de objetos*.
Algunas clases concretas que implementan esta interface son: HashSet, LinkedHashSet, TreeSet.

##### List Collection
Es una interface que extiende la interface Collection agregando todos los mensajes que tienen que ver con colecciones que **tienen un orden**.
Algunas clases concretas que implementan esta interface son: ArrayList, LinkedList y Vector.
#### Método lookup
Se denomina así a la estrategia que los lenguajes utilizan para saber dónde está el código que debe ejecutarse cuando un objeto recibe un mensaje. En el caso más sencillo,  el código que debe ejecutarse está en la clase del objeto. En el peor de los casos, si una clase está heredando de otra, el código puede estar en la clase del padre.
#### Garbage collector
Es una forma automatizada de gestionar la memoria.  El garbage collector recolecta a todos aquellos objetos que no están siendo referenciados por nadie hace un "largo" tiempo. Dicho de otro modo, se encarga de matar a los objetos innecesarios.
Gracias a este mecanismo, el desarrollador puede olvidarse de la gestión de la memoria.
## Generalizando a Gary
### Décima primera iteración
#### Dominio
Hasta ahora sabíamos que los gatos podían visitar varios lugares. Ahora nos interesa poder preguntarle a un gato cuántos metros lleva recorrido y cuántos viajes hizo. Además, es importante que se tenga el dato de la hora y fecha en que el gato realizó el viaje, así como también el destino.
#### Diseño en objetos
Como ahora nos interesa que un gato pueda responder a los mensajes `cantidadDeVisitas()` y `metrosRecorridos()`, vamos a necesitar tener en cuenta algunas cosas extras que hasta ahora no habíamos considerado.
En primer lugar, probablemente se nos ocurriría agregar dos contadores en la clase Gato: un contador de visitas y otro de metros recorridos. ¿Pero qué sucedería si me piden el listado de los lugares que visitó el gato? ¡Es muy probable que me lo pidan! ¿Y cómo sabemos la fecha y la hora exacta cuando recorrió los lugares? ¡Es muy complicado! Por este motivo, descartamos de entrada la alternativa de los contadores.
Podríamos, entonces, pensar en la posibilidad de que un gato tenga una colección de **visitas**. Cuando se le envié el mensaje `cantidadDeVisitas()` habría que contar la cantidad de elementos que tiene la colección y listo. Y cuando se le pregunte por los metros recorridos, se podrían sumar los metros que el gato tuvo que desplazarse para realizar esa visita.  ¡Momento! Una nueva entidad: la clase **Visita**.
La clase visita es a quien le vamos a dar la responsabilidad de guardar la fecha, la hora y el lugar, así como también la distancia que tuvo que recorrer el gato para realizar la visita.
¿Y cuál debería ser el mensaje principal?  `unGato.realizaVisita(unaVisita)` por ejemplo. ¿Y qué sucede cuando un gato realiza una visita? Debe caminar hasta el lugar, pero también guardar esa visita en su colección de visitas.

¡Todo listo!
#### Implementación
Comencemos por la clase Visita:
```java
import java.time.LocalDate;

public class Visita {
    private Lugar lugar;
    private LocalDate fechaYHora;
    private int distanciaRecorrida;

    public Visita(Lugar lugar){
        this.lugar = lugar;
    }

    private void marcarFechaYHora(){
        this.fechaYHora = LocalDate.now();
    }

    public void concretarPor(Gato unGato){
        marcarFechaYHora();
        this.distanciaRecorrida = unGato.caminaHacia(this.lugar);
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
}
```
Algunas consideraciones sobre esta implementación:
- Estamos obligando a instanciar una visita con un lugar en particular. Esto lo podemos apreciar en el constructor.
- Cuando la visita es concretada por un gato, se deja la huella de la fecha y hora. Para ello, se usa el método `now` de la clase `LocalDate`, que devuelve un objeto. 
- Además de dejar la huella de la fecha y hora, se lo manda a caminar al gato hacia el lugar. Caminar implica desplazarse, y por lo tanto guardamos ese desplazamiento en un atributo `distanciaRecorrida`

Ahora veamos como queda la clase Gato:
```java
import java.util.ArrayList;
import java.util.List;

public class Gato {
    private String nombre;
    private Color color;
    private int energia;
    private int hambre;
    private int diversion;
    private double temperaturaCorporal;
    private Prenda prenda;
    private Punto punto;
    private List<Visita> visitas;

    public Gato(String nombre, Color color){
        this.nombre = nombre;
        this.color = color;
        this.energia = 10;
        this.hambre = 10;
        this.diversion = 0;
        this.temperaturaCorporal = 37.0;
        this.prenda = null;
        this.punto = new Punto(0,0);
        this.visitas = new ArrayList<>();
    }

    public void disminuirEnergiaEn(int algunasUnidades){
        this.energia -= algunasUnidades;
    }

    public void aumentarHambreEn(int algunasUnidades){
        this.hambre += algunasUnidades;
    }

    public void camina(int unosMetros){
        disminuirEnergiaEn(unosMetros/70);
        aumentarHambreEn(2 * (unosMetros/40));
    }

    public int caminaHacia(Lugar unLugar){
        Punto ubicacionDelLugar = unLugar.getPunto();
        int distanciaAbosluta = ubicacionDelLugar.distanciaAbsolutaEntre(this.punto);
        camina(distanciaAbosluta);
        this.punto = ubicacionDelLugar;
        unLugar.serVisitadoPor(this);
        return distanciaAbosluta;
    }

    private void agregarVisita(Visita visita){
        this.visitas.add(visita);
    }

    public int metrosRecorridos(){
        return this.visitas.stream().mapToInt(v -> v.getDistanciaRecorrida()).sum();
    }

    public int cantidadDeVisitas(){
        return this.visitas.size();
    }

    public void realizaVisita(Visita visita){
        agregarVisita(visita);
        visita.concretarPor(this);
    }
}
```
- Cuando se quiere declarar una colección/lista, siempre hay que definir el tipo de objetos que contendrá. En este caso lo indicamos con `List<Visita> visitas;`, pero genéricamente sería `List<NombreClase> miLista;`
- La lista siempre debe ser inicializada en el constructor porque también es un objeto. Como `List` es una interface y no puede ser instanciada, debemos instanciar alguna clase concreta que la implemente. Esto lo podemos ver en la línea `this.visitas = new ArrayList<>();`
- Para agregar elementos a una colección, debemos enviarle el mensaje `add` a la misma, pasándole como parámetro el objeto que queremos agregar. Esto lo podemos ver en el método `agregarVisita(Visita unaVisita)`
- Para saber la cantidad de elementos de una colección, debemos enviarle el mensaje `size`, tal cual se puede ver en el método `cantidadDeVisitas()`
- El método `metrosRecorridos()` quizás sea el más complejo de todos. Si nos detenemos un segundo y miramos su implementación, podremos observar que le enviamos un mensaje llamado `stream` a la colección. Lo cierto es que es un getter porque stream es un objeto que, en Java, se encarga de ciertas cosas para el manejo de colecciones.
Luego de obtener el objeto stream, le enviamos el mensaje `mapToInt` que se encarga de convertir la lista de visitas en una lista de enteros según el método que le mandemos por parámetro. En este caso,  le estamos pidiendo a cada visita que nos devuelva su distancia recorrida y eso lo vemos con la sentencia `v -> v.getDistanciaRecorrida()` donde `v` es una visita de la colección. Por lo tanto, pasaríamos de tener una colección de visitas a una colección de distancias recorridas.
Por último, le enviamos el mensaje `sum` que se encarga de sumar todos los elementos de la colección y devolver un resultado.

#### Tests
##### En Java
Vamos a hacer que Gary realice dos visitas para comprobar si realmente se están guardando las visitas.
```java
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GatoTest {
    private Gato gary;

    @Before
    public void init(){
        this.gary = new Gato("Gary", Color.NARANJA);
    }

    @Test
    public void garyVisitaDosLugaresTest(){
        Lugar laCasaDePedro = new CasaDelVecino(new Punto(180,50));
        Lugar cocina = new Cocina(new Punto(15,14));

        Visita visitaAPedro = new Visita(laCasaDePedro);
        Visita visitaALaCocina = new Visita(cocina);

        this.gary.realizaVisita(visitaAPedro);
        this.gary.realizaVisita(visitaALaCocina);

        Assert.assertEquals(2, this.gary.cantidadDeVisitas());
        Assert.assertEquals(354, this.gary.metrosRecorridos());
    }
}
```
