package dev.akarshmi.quilink.shortener.serviceImpl;

import dev.akarshmi.quilink.common.mapper.UrlMappingMapperInterface;
import dev.akarshmi.quilink.shortener.document.UrlMapping;
import dev.akarshmi.quilink.shortener.dto.UrlMappingRequest;
import dev.akarshmi.quilink.shortener.dto.UrlMappingResponse;
import dev.akarshmi.quilink.shortener.util.Base62encoder;
import dev.akarshmi.quilink.shortener.util.SnowflakeIdGenerator;
import dev.akarshmi.quilink.shortener.repository.UrlMappingRepository;
import dev.akarshmi.quilink.shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {
    private final UrlMappingRepository urlMappingRepository;
    private final UrlMappingMapperInterface urlMappingMapper;
    private final SnowflakeIdGenerator  snowflakeIdGenerator;


    @Override
    public UrlMappingResponse shortener(UrlMappingRequest request) {
        long id  = snowflakeIdGenerator.nextId();
        String shortCode = Base62encoder.encode(id);

        UrlMapping urlMapped = UrlMapping.builder()
                .longUrl(request.longUrl())
                .shortCode(shortCode)
                .snowflakeId(id)
                .isActive(true)
                .createdAt(Instant.now())
                .build();

        UrlMapping saved = urlMappingRepository.save(urlMapped);
        System.out.println(saved.toString());
        return urlMappingMapper.urlMappingToUrlMappingResponse(urlMapped);
    }
}
