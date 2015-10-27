## MockMvc e REST Assured

In version 3.2 Spring introduced MockMvc which is a huge improvement and enables us to easily implement this type of tests. When combined with REST Assured (“Java DSL for easy testing of REST services”) we get to use fluent API (including given, when, then) to create code that is expressive as well as easy to read and understand.

```java

@Test public void
getHello() {
    given().
    when().
        get(HELLO).
    then().
        statusCode(HttpServletResponse.SC_OK).
        contentType("application/json").
        body(equalTo("Hello world!"));
} 
 
@Test public void
getHelloWithParam() {
    given().
        param("name", "coder").
    when().
        get(HELLO).
    then().
        statusCode(HttpServletResponse.SC_OK).
        contentType("application/json").
        body(equalTo("Hello coder!"));
}


@Test public void
failPostToHello() {
    given().
    when().
        post(HELLO).
    then().
        statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        "classpath:test-mvc-dispatcher-servlet.xml")
public class HelloControllerTest {
     
    private MockMvc mockMvc;
     
    @Autowired
    private WebApplicationContext context;
     
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
        RestAssuredMockMvc.mockMvc = mockMvc;
    }
    ...
}


```
