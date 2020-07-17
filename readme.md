# Doku Java Library
This Library use for merchant who want to integrate with DOKU

## Generate Payment Code
This part telling about how to generate payment code

### 1. Install Doku java library
For install doku java library, add depedency to your pom : 

```
<dependency>
    <groupId>com.doku</groupId>
    <artifactId>sdk</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 2. Setup Configuration
Setup the configuration, this object set merchant credential and environment server. Doku have two environment server you can choose "ServerLocation.SANDBOX.getUrl()" or  "ServerLocation.PROD.getUrl()".

```
import com.doku.sdk.pojo.SetupConfiguration;
import com.doku.sdk.pojo.ServerLocation;

SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("clientID")
        .merchantName("merchantName")
        .sharedKey("sharedKey")
        .serverLocation(ServerLocation.SANDBOX.getUrl()) 
        .build();
```
### 3. Prepare your request data
Setup your request data, this data use to send the request to doku core.

```
import com.doku.sdk.dto.payment.request.*;

PaymentCodeRequestDto.builder()
        .client(ClientRequestDto.builder()
                .id("clientId")
                .build())
        .order(OrderRequestDto.builder()
                .invoiceNumber("invoiceNumber")
                .amount(100000L)
                .build())
        .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                .expiredTime(60)
                .reusableStatus(false)
                .info1("Nama Toko")
                .info2("Kontak Toko")
                .info3("Greetings Toko")
                .build())
        .sharedKey(sharedKey)
        .customer(CustomerRequestDto.builder()
                .name("customerName")
                .email("emailCustomer")
                .build())
        .generateWords()
        .build();
```
### 4. Generate payment code
For generate payment code use the syntax follow :

#### This is for Mandiri : 
```

import com.doku.sdk.service;
import com.doku.sdk.dto.payment.response.*;

PaymentCodeResponseDto paymentCodeResponseDto =new GeneratePaycodeServices().generateMandiri(setupConfiguration, paymentCodeRequestDto);

```

#### This is for Mandiri Syariah: 

```
import com.doku.sdk.service;
import com.doku.sdk.dto.payment.response.*;

PaymentCodeResponseDto paymentCodeResponseDto = new GeneratePaycodeServices().generateMandiriSyariah(setupConfiguration, paymentCodeRequestDto);

```


#### Example full code : 
```
import com.doku.sdk.dto.payment.request.*;
import com.doku.sdk.dto.payment.response.*;
import com.doku.sdk.pojo.SetupConfiguration;
import com.doku.sdk.pojo.ServerLocation;
import com.doku.sdk.service;


SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("clientID")
        .merchantName("merchantName")
        .sharedKey("sharedKey")
        .serverLocation(ServerLocation.SANDBOX.getUrl()) 
        .build();

PaymentCodeRequestDto.builder()
        .client(ClientRequestDto.builder()
                .id("clientId")
                .build())
        .order(OrderRequestDto.builder()
                .invoiceNumber("invoiceNumber")
                .amount(100000L)
                .build())
        .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                .expiredTime(60)
                .reusableStatus(false)
                .info1("Nama Toko")
                .info2("Kontak Toko")
                .info3("Greetings Toko")
                .build())
        .sharedKey(sharedKey)
        .customer(CustomerRequestDto.builder()
                .name("customerName")
                .email("emailCustomer")
                .build())
        .generateWords()
        .build();


PaymentCodeResponseDto paymentCodeResponseDto = new GeneratePaycodeServices().generateMandiri(setupConfiguration, paymentCodeRequestDto);
```
## Notify Payment
This part telling about how to recieve notification

### 1. Request 
In notify process merchant create notify endpoint that use class NotifyRequestDto prepared by doku, this endpoint will hit by doku.



### 2. Response 
After hit by Doku, merchant must return response. Response format can use NotifyResponseDto class.
```
import com.doku.sdk.dto.notify.response.*;
 
NotifyResponseDto.builder()
                .client(ClientResponseDto.builder()
                        .id("clientId").build())
                .order(OrderResponseDto.builder()
                        .amount(1000L)
                        .invoiceNumber("InvoiceNumber")
                        .build())
                .virtualAccountInfo(VirtualAccountInfoResponseDto.builder()
                        .virtualAccountNumber("vaNumber")
                        .build())
                .security(SecurityResponseDto.builder()
                        .checkSum("checkSum")
                        .build())
                .build();
```