- encapsular los metodos que sean para la base de datos

RESEÑA Cafeteria Manager
- **createProduct** debe crear un producto (con cantidad) si no esta en inventario lo crea, sino tira error
  no debería ser poner nombre, cantidad, categoria...? las validaciones pueden ir en otro metodo
- **deleteProduct** debe eliminar el producto por la ID
  quiza el nombre pueda ser deleteProductbyID(?
  !Estaba viendo que deleteProduct y removeProduct en cafeteriaMenu hacen lo mismo. Incluso está en cafetería storage, al menos el nombre.
- **editProduct** debe editar el producto
  si lo hace wiiii
- **createCombo** crea un combo agarra productos de donde tenga que agarrar y los hace
 <u> conclusión: </u>
  cafeteriaManager deberia llamarse diferentes, al menos en mi opinion, porque lo que hace es verificar sis e puede hacer o no,
  si se puede lo hace, si no tira excepciones o advertencias. Entonces, cambiar el nombre

RESEÑA CafeteriaMenu
- el constructor, porque se repite?
