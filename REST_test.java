@Test
public void test_ProductsEndpoint()
  throws ClientProtocolException, IOException {
  
    // Given

    String jsonMimeType = "application/json";
    int albumID = getRandomNumberInRange(1, 40);
    HttpUriRequest request = new HttpGet( "https://mezzo-4413.mybluemix.net/api/products/" + albumID );
 
    // When
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
 
    // Then - ensure returned data is JSON
     String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
   assertEquals( jsonMimeType, mimeType );
}

@Test
public void test_OrdersEndpoint()
  throws ClientProtocolException, IOException {
  
    // Given
    String jsonMimeType = "application/json";
    int albumID = getRandomNumberInRange(1, 40);
    HttpUriRequest request = new HttpGet( "https://mezzo-4413.mybluemix.net/api/orders/?aid=" + albumID );
 
    // When
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
 
    // Then ensure returned data is JSON
    assertThat(
       String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
   assertEquals( jsonMimeType, mimeType );
}
/**
*Utility method
*/
private static int getRandomNumberInRange(int min, int max) {

    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }