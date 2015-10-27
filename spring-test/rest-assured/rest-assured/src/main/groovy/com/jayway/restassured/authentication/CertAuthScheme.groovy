/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.restassured.authentication

import com.jayway.restassured.internal.http.HTTPBuilder
import org.apache.http.conn.ssl.SSLSocketFactory
import org.apache.http.conn.ssl.X509HostnameVerifier

import java.security.KeyStore

class CertAuthScheme implements AuthenticationScheme {
  def pathToKeyStore
  def String password
  def String keystoreType = KeyStore.getDefaultType()
  def int port = -1
  def KeyStore trustStore
  def X509HostnameVerifier x509HostnameVerifier
  def SSLSocketFactory sslSocketFactory;

  @Override
  void authenticate(HTTPBuilder httpBuilder) {
    httpBuilder.auth.certificate(pathToKeyStore, password, keystoreType, port, trustStore, x509HostnameVerifier, sslSocketFactory)
  }
}
