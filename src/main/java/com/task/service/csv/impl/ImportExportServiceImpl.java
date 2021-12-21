package com.task.service.csv.impl;

import com.task.service.csv.ImportExportService;
import com.task.service.csv.model.Address;
import com.task.service.csv.model.CombineXml;
import com.task.service.csv.service.ReadAndWriteService;
import com.task.service.manager.RequestManager;
import com.task.service.model.CovidResponse;
import com.task.service.model.HotelGroup;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportExportServiceImpl implements ImportExportService {

    private final ReadAndWriteService<Address> readAndWriteService;

    private final RequestManager requestManager;

    @Override
    public ResponseEntity<?> importCsv(final MultipartFile multipartFile) {

        try (Reader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            final List<Address> addressList = readAndWriteService.read(reader, Address.class);

            for (Address address : addressList) {
                String country = address.getCountry();

                final HotelGroup testHotel = requestManager.makeGetHotelRequest(address.getCity()).stream()
                        .filter(h -> h.getEntities().size() > 2).findFirst().orElse(null);

                final CovidResponse testCovid =
                        requestManager.makeGetCovidStatsRequest(address.getCountry(), address.getDate()).stream()
                                .filter(t -> t.getProvince().equals(country)).findFirst().orElse(null);

                final CombineXml combineXml = new CombineXml();
                combineXml.setCity(address.getCity());
                combineXml.setCountry(address.getCountry());
                combineXml.setCovidResults(testCovid);
                if (ObjectUtils.isNotEmpty(testHotel)) {
                    combineXml.setHotels(testHotel.getEntities());
                }

                File file = new File(country + ".xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(combineXml.getClass());
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(combineXml, file);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File is wrong");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

}
