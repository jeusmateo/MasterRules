- encapsular los metodos que sean para la base de datos

RESEÑA Cafeteria Manager
- **add, create, remove/delete** de donde?
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
- add product a donde? y asi en varios 
- es correcto dejar las expeciones en metodos como addProduct, removeProduct, editProduct?
- removeProdut: el método creo que podría enfocarse solo en eliminar el producto
- editProduct: lo veo bien, podría modularizarse más?
- getProductsByType SE USA EN DONDE? ya entendi, peroi mejor expliquenmelo
- registeredComboCount esta cosa confunde y no se que es, o sea si, pero ugh
- get/setTitle va a servir de algo?
- lo demás se ve bien

RESEÑA CafeteriaStorage
- constrictores dupllicados, porque? 
- addProduct, REMOVE a donde?
- updateStock o addToStock?
- los siguientes, que uso esta teniendo?
  - searchProduct
  - hasEnoughStock
  - removeFromStock
  - addToStock
  - falta implementar searchProduct, o es el searchProduct?

RESEÑA cashFlow
lo veo bien :DDDD

RESEÑA CASHREGISTERAUDITREPORT
- removeCashOutFlowReport no se debería usar ni removeCashInFlowReport
- calculateFinalCashAmount creo q se puede modularizar
- lo demas lo veo bien :DDD

RESEÑA CashRegisterAuditReportManager
- TODO ESTA EN LA CLASE DE AQUI EN ADELANTE

