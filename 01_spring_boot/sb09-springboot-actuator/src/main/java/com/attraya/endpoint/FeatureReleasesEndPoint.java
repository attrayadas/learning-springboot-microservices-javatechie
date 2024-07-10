package com.attraya.endpoint;

import com.attraya.dto.ProdRelease;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Endpoint(id = "releases")
public class FeatureReleasesEndPoint {

    List<ProdRelease> prodReleases = new ArrayList<>();

    @WriteOperation
    public void addNewReleaseInfo(@Selector String crq, @Selector String releaseDt, String features){
        ProdRelease release = ProdRelease.builder()
                .changeRequestNo(crq)
                .releaseDate(releaseDt)
                .features(Arrays.stream(features.split(",")).toList()).build();
        prodReleases.add(release);
    }

    @ReadOperation
    public List<ProdRelease> getAllReleases(){
        return prodReleases;
    }

    @ReadOperation
    public ProdRelease getReleaseByCRQ(@Selector String crq){
        return prodReleases.stream().filter(prodRelease -> prodRelease.getChangeRequestNo().equals(crq))
                .findAny().get();
    }

    @DeleteOperation
    public void deleteRelease(@Selector String crq){
        prodReleases.remove(getReleaseByCRQ(crq));
    }
}
