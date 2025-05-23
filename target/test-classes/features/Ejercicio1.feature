@casosMercadoLibre
Feature: Validar funcionalidad del buscador

  @test
  Scenario: Realizar Busqueda en Mercado Libre
    Given ingreso a la aplicacion mobile
    When entro a realizar una busqueda en el search
    And ingreso la busqueda de "Ipad pro"
    Then busco algun ipad de mi interes "Apple Ipad Pro 12.9  (2021) 256gb Wifi Space Gray Gris Claro"

  @test2
  Scenario: Realizar  en Mercado Libre
    Given ingreso a la aplicacion mobile
    When busco el elemento "Suscribirme a Meli+" en la pagina principal
    And selecciono la opcion "Elegir mi plan ideal"
    And selecciono la opcion "Suscribirme a Meli+ Esencial"
    Then valido titulo "Ingresa tu e-mail, teléfono o usuario de Mercado Libre"


  @test3
  Scenario: Realizar Busqueda en Mercado Libre
    Given ingreso a la aplicacion mobile
    When entro a realizar una busqueda en el search
    And ingreso la busqueda de "Iphone 16 pro"
    And busco el segundo elemento de la pantalla
    And selecciono el numero de rating
    Then valido que la calificacion visible

