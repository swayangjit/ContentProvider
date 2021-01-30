
A sample application that demonstrates sharing data with other applications using ContetProvider.
#### Important points to consider while creating a custom content provider

 - First create your custom permission and **provide a namespace to those permissions**.

    ``<permission  
  android:name="com.github.provider.READ_DATABASE"  
  android:label="@string/app_read"  
  android:protectionLevel="normal">  
</permission>  ``

    ``<permission  
  android:name="com.github.provider.WRITE_DATABASE"  
  android:label="@string/app_write"  
  android:protectionLevel="normal">  
</permission>``

- While mentioning the provider in the ``AndroidManifest.xml`` mark the provider as ``exported = true`` and add the read and write permissions to the Provider.

	``<provider  
  android:name=".providers.TelemetryProvider"  
  android:authorities="com.github.provider"  
  android:enabled="true"  
  android:exported="true"  
  android:multiprocess="true"  
  android:readPermission="com.github.provider.READ_DATABASE"  
  android:writePermission="com.github.provider.WRITE_DATABASE"/>``