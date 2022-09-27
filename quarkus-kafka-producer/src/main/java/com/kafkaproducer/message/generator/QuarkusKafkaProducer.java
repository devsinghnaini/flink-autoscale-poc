package com.kafkaproducer.message.generator;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@ApplicationScoped
public class QuarkusKafkaProducer {

    Random random;

    int low;

    @ConfigProperty(name="total.number.of.unique.kafka.keys")
    int high;

    @ConfigProperty(name="length.of.payload.to.be.generated.bytes")
    int lengthOfString;

    @ConfigProperty(name="payload.generation.frequency.ms")
    int payloadGenerationFrequency;

    public QuarkusKafkaProducer()
    {
        random = new Random();
        low =1;
    }

    @Outgoing("incoming_kafka_topic_test")
    public Multi<Record<String,String>> generateMessages() {
        //return
        return Multi.createFrom().ticks().every(Duration.ofMillis(payloadGenerationFrequency))
                .onOverflow().drop()
                .map(tick ->
                {
                    String key = String.valueOf(random.nextInt(high-low)+low);
                    String val = new String("{\n" +
                            "  \"resourceType\": \"Observation\",\n" +
                            "  \"id\": \"blood-pressure\",\n" +
                            "  \"meta\": {\n" +
                            "    \"profile\": [\n" +
                            "      \"http://hl7.org/fhir/StructureDefinition/vitalsigns\"\n" +
                            "    ]\n" +
                            "  },\n" +
                            "  \"text\": {\n" +
                            "    \"status\": \"generated\",\n" +
                            "    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: blood-pressure</p><p><b>meta</b>: </p><p><b>identifier</b>: urn:uuid:187e0c12-8dd2-67e2-99b2-bf273c878281</p><p><b>basedOn</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Blood pressure systolic &amp; diastolic <span>(Details : {LOINC code '85354-9' = 'Blood pressure panel with all children optional', given as 'Blood pressure panel with all children optional'})</span></p><p><b>subject</b>: <a>Patient/example</a></p><p><b>effective</b>: 17/09/2012</p><p><b>performer</b>: <a>Practitioner/example</a></p><p><b>interpretation</b>: Below low normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'L' = 'Low', given as 'low'})</span></p><p><b>bodySite</b>: Right arm <span>(Details : {SNOMED CT code '368209003' = 'Right upper arm', given as 'Right arm'})</span></p><blockquote><p><b>component</b></p><p><b>code</b>: Systolic blood pressure <span>(Details : {LOINC code '8480-6' = 'Systolic blood pressure', given as 'Systolic blood pressure'}; {SNOMED CT code '271649006' = 'Systolic blood pressure', given as 'Systolic blood pressure'}; {http://acme.org/devices/clinical-codes code 'bp-s' = 'bp-s', given as 'Systolic Blood pressure'})</span></p><p><b>value</b>: 107 mmHg<span> (Details: UCUM code mm[Hg] = 'mmHg')</span></p><p><b>interpretation</b>: Normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'N' = 'Normal', given as 'normal'})</span></p></blockquote><blockquote><p><b>component</b></p><p><b>code</b>: Diastolic blood pressure <span>(Details : {LOINC code '8462-4' = 'Diastolic blood pressure', given as 'Diastolic blood pressure'})</span></p><p><b>value</b>: 60 mmHg<span> (Details: UCUM code mm[Hg] = 'mmHg')</span></p><p><b>interpretation</b>: Below low normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'L' = 'Low', given as 'low'})</span></p></blockquote></div>\"\n" +
                            "  },\n" +
                            "  \"identifier\": [\n" +
                            "    {\n" +
                            "      \"system\": \"urn:ietf:rfc:3986\",\n" +
                            "      \"value\": \"urn:uuid:187e0c12-8dd2-67e2-99b2-bf273c878281\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"basedOn\": [\n" +
                            "    {\n" +
                            "      \"identifier\": {\n" +
                            "        \"system\": \"https://acme.org/identifiers\",\n" +
                            "        \"value\": \"1234\"\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"status\": \"final\",\n" +
                            "  \"category\": [\n" +
                            "    {\n" +
                            "      \"coding\": [\n" +
                            "        {\n" +
                            "          \"system\": \"http://terminology.hl7.org/CodeSystem/observation-category\",\n" +
                            "          \"code\": \"vital-signs\",\n" +
                            "          \"display\": \"Vital Signs\"\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"code\": {\n" +
                            "    \"coding\": [\n" +
                            "      {\n" +
                            "        \"system\": \"http://loinc.org\",\n" +
                            "        \"code\": \"85354-9\",\n" +
                            "        \"display\": \"Blood pressure panel with all children optional\"\n" +
                            "      }\n" +
                            "    ],\n" +
                            "    \"text\": \"Blood pressure systolic & diastolic\"\n" +
                            "  },\n" +
                            "  \"subject\": {\n" +
                            "    \"reference\": \"Patient/example\"\n" +
                            "  },\n" +
                            "  \"effectiveDateTime\": \"2012-09-17\",\n" +
                            "  \"performer\": [\n" +
                            "    {\n" +
                            "      \"reference\": \"Practitioner/example\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"interpretation\": [\n" +
                            "    {\n" +
                            "      \"coding\": [\n" +
                            "        {\n" +
                            "          \"system\": \"http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation\",\n" +
                            "          \"code\": \"L\",\n" +
                            "          \"display\": \"low\"\n" +
                            "        }\n" +
                            "      ],\n" +
                            "      \"text\": \"Below low normal\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"bodySite\": {\n" +
                            "    \"coding\": [\n" +
                            "      {\n" +
                            "        \"system\": \"http://snomed.info/sct\",\n" +
                            "        \"code\": \"368209003\",\n" +
                            "        \"display\": \"Right arm\"\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  },\n" +
                            "  \"component\": [\n" +
                            "    {\n" +
                            "      \"code\": {\n" +
                            "        \"coding\": [\n" +
                            "          {\n" +
                            "            \"system\": \"http://loinc.org\",\n" +
                            "            \"code\": \"8480-6\",\n" +
                            "            \"display\": \"Systolic blood pressure\"\n" +
                            "          },\n" +
                            "          {\n" +
                            "            \"system\": \"http://snomed.info/sct\",\n" +
                            "            \"code\": \"271649006\",\n" +
                            "            \"display\": \"Systolic blood pressure\"\n" +
                            "          },\n" +
                            "          {\n" +
                            "            \"system\": \"http://acme.org/devices/clinical-codes\",\n" +
                            "            \"code\": \"bp-s\",\n" +
                            "            \"display\": \"Systolic Blood pressure\"\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      },\n" +
                            "      \"valueQuantity\": {\n" +
                            "        \"value\": 107,\n" +
                            "        \"unit\": \"mmHg\",\n" +
                            "        \"system\": \"http://unitsofmeasure.org\",\n" +
                            "        \"code\": \"mm[Hg]\"\n" +
                            "      },\n" +
                            "      \"interpretation\": [\n" +
                            "        {\n" +
                            "          \"coding\": [\n" +
                            "            {\n" +
                            "              \"system\": \"http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation\",\n" +
                            "              \"code\": \"N\",\n" +
                            "              \"display\": \"normal\"\n" +
                            "            }\n" +
                            "          ],\n" +
                            "          \"text\": \"Normal\"\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"code\": {\n" +
                            "        \"coding\": [\n" +
                            "          {\n" +
                            "            \"system\": \"http://loinc.org\",\n" +
                            "            \"code\": \"8462-4\",\n" +
                            "            \"display\": \"Diastolic blood pressure\"\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      },\n" +
                            "      \"valueQuantity\": {\n" +
                            "        \"value\": 60,\n" +
                            "        \"unit\": \"mmHg\",\n" +
                            "        \"system\": \"http://unitsofmeasure.org\",\n" +
                            "        \"code\": \"mm[Hg]\"\n" +
                            "      },\n" +
                            "      \"interpretation\": [\n" +
                            "        {\n" +
                            "          \"coding\": [\n" +
                            "            {\n" +
                            "              \"system\": \"http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation\",\n" +
                            "              \"code\": \"L\",\n" +
                            "              \"display\": \"low\"\n" +
                            "            }\n" +
                            "          ],\n" +
                            "          \"text\": \"Below low normal\"\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}");
                    return Record.of(key,val);
                });
    }

}
