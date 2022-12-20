package com.jomardev25.ecommerce.commonservice.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jomardev25.ecommerce.commonservice.entity.categories.ApparelCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.GenderCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.PriceRangeCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.ProductBrandCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.SortByCategory;
import com.jomardev25.ecommerce.commonservice.entity.images.ApparelImages;
import com.jomardev25.ecommerce.commonservice.entity.images.BrandImages;
import com.jomardev25.ecommerce.commonservice.entity.images.CarouselImages;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;
import com.jomardev25.ecommerce.commonservice.repository.categories.ApparelCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.GenderCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.PriceRangeCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.ProductBrandCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.SortByCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.ApparelImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.BrandImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.CarouselImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.info.ProductInfoRepository;
import com.jomardev25.ecommerce.commonservice.service.LoadSampleDataService;

@Service
public class LoadSampleDataServiceImpl implements LoadSampleDataService {

	enum FileNameType {
        SORT_BY, PRICE_RANGE
    }

    private final int WOMEN = 1;
    private final int MEN = 2;
    private final int GIRLS = 3;
    private final int BOYS = 4;
    private final int HOME_AND_LIVING = 5;
    private final int ESSENTIALS = 6;

    private final String DATA_DIRECTORY = "sample_data";
    private final String MAIN_SCREEN_DATA = "main-screen-data.txt";
    private final String PRICE_RANGE_DATA = "price-range-data.txt";
    private final String SORT_BY_DATA = "sortby-data.txt";
    private final String WEB_DATA = "web-data.txt";

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private GenderCategoryRepository genderCategoryRepository;

    @Autowired
    private ApparelCategoryRepository apparelCategoryRepository;

    @Autowired
    private ProductBrandCategoryRepository productBrandCategoryRepository;

    @Autowired
    private BrandImagesRepository brandImagesRepository;

    @Autowired
    private ApparelImagesRepository apparelImagesRepository;

    @Autowired
    private CarouselImagesRepository carouselImagesRepository;

    @Autowired
    private SortByCategoryRepository sortByCategoryRepository;

    @Autowired
    private PriceRangeCategoryRepository priceRangeCategoryRepository;

    @Transactional
    public boolean loadSampleData() {
        System.out.println("Loading test data in to database...");

        if (!loadFixedPatternData(String.format("%s/%s", DATA_DIRECTORY, SORT_BY_DATA), FileNameType.SORT_BY)) {
            return false;
        }

        if (!loadFixedPatternData(String.format("%s/%s", DATA_DIRECTORY, PRICE_RANGE_DATA), FileNameType.PRICE_RANGE)) {
            return false;
        }

        List<PriceRangeCategory> priceRangeCategoryList = priceRangeCategoryRepository.findAll();

        if(!priceRangeCategoryList.isEmpty()) {
            //for(PriceRangeCategory priceRangeCategory: priceRangeCategoryList) {
                // System.out.println("STORED PRICE RANGE CATEGORY id = " + priceRangeCategory.getId() + "type = "+ priceRangeCategory.getType());
            //}
        } else {
            System.out.println("PRICE RANGE CATEGORY LIST IS EMPTY..Hence exiting.....");
            return false;
        }

        try {

            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(String.format("%s/%s", DATA_DIRECTORY, WEB_DATA));

            if (inputStream == null) {
                System.out.println("Inputstream for test data is empty....");
                return false;
            }

            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                String[] result = line.split("\\|");
                String gender = result[1];
                String apparel = result[2];
                String brandName = result[3];
                String productName = result[4];
                String price = result[5];
                String fileName = result[6];
                String imageURL = result[7];
                String imageLocalPath = replaceSpacesWithUnderscore(String.format("%s/%s/%s", gender, apparel, fileName));

                GenderCategory genderCategory = genderCategoryRepository.findByType(gender);
                ApparelCategory apparelCategory = apparelCategoryRepository.findByType(apparel);
                ProductBrandCategory productBrandCategory = productBrandCategoryRepository.findByType(brandName);

                if (genderCategory == null) {
                    int genderId;

                    switch (gender.toLowerCase()) {
                        case "women":
                            genderId = WOMEN;
                            break;
                        case "men":
                            genderId = MEN;
                            break;
                        case "boys":
                            genderId = BOYS;
                            break;
                        case "girls":
                            genderId = GIRLS;
                            break;
                        case "home & living":
                            genderId = HOME_AND_LIVING;
                            break;
                        case "essentials":
                            genderId = ESSENTIALS;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + gender.toLowerCase());
                    }
                    genderCategory = new GenderCategory(genderId, gender);
                    genderCategoryRepository.save(genderCategory);
                }

                if (apparelCategory == null) {
                    apparelCategory = new ApparelCategory(apparel);
                    apparelCategoryRepository.save(apparelCategory);
                }

                if (productBrandCategory == null) {
                    productBrandCategory = new ProductBrandCategory(brandName);
                    productBrandCategoryRepository.save(productBrandCategory);
                }

                Optional<PriceRangeCategory> priceRangeCategory = findPriceRangeCategory(Integer.parseInt(price));

                if(priceRangeCategory.isPresent()) {
                    ProductInfo productInfo = new ProductInfo(1, productName, generateRandomDate(),
                            productBrandCategory, genderCategory, apparelCategory, priceRangeCategory.get(),
                            Integer.parseInt(price), generateRandomInt(1, 10),
                            generateRandomInt(2, 5), generateRandomFloat(0, 5, 1),
                            true, imageLocalPath, imageURL);
                    productInfoRepository.save(productInfo);
                } else {
                    System.out.println("ERROR: Unable to find price range category......");
                    return false;
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadHomeScreenData();
    }

    @Transactional
    public boolean loadHomeScreenData() {
        System.out.println("Loading website data in to database...");

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(String.format("%s/%s", DATA_DIRECTORY, MAIN_SCREEN_DATA));

            if (inputStream == null) {
                System.out.println("Inputstream for website data is empty....");
                return false;
            }

            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                String[] separatedData = line.split("\\|");
                String type = separatedData[0];
                String imageLocalPath = separatedData[1];
                String imageURL = separatedData[2];
                String title = separatedData.length > 3 ? separatedData[3] : null;
                String gender = separatedData.length > 4 ? separatedData[4] : null;

                switch (type) {
                    case "brand":
                        BrandImages brandImages = new BrandImages(title, imageLocalPath, imageURL);
                        ProductBrandCategory productBrandCategory = productBrandCategoryRepository.findByType(title);
                        if (productBrandCategory != null) {
                            brandImages.setProductBrandCategory(productBrandCategory);
                            brandImagesRepository.save(brandImages);
                        }
                        break;
                    case "category":
                        ApparelImages apparelImages = new ApparelImages(title, imageLocalPath, imageURL);
                        ApparelCategory apparelCategory = apparelCategoryRepository.findByType(title);

                        GenderCategory genderCategory = genderCategoryRepository.findByType(gender);
                        if (apparelCategory != null) {
                            apparelImages.setApparelCategory(apparelCategory);
                            apparelImages.setGenderCategory(genderCategory);
                            apparelImagesRepository.save(apparelImages);
                        }
                        break;
                    case "carousel":
                        StringBuilder link = null;
                        if (title != null) {
                            title += ",";
                            String[] categories = title.split(",");
                            link = new StringBuilder("genders=");
                            for (String category : categories) {
                                genderCategory = genderCategoryRepository.findByType(category);
                                link.append(genderCategory.getId()).append(",");
                            }
                        }

                        CarouselImages carouselImages = new CarouselImages(link != null ? link.toString() : null, imageLocalPath, imageURL);
                        carouselImagesRepository.save(carouselImages);
                        break;
                    default:
                        System.out.println("Unsupported Type");
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Transactional
    public boolean loadFixedPatternData(String filename, FileNameType filenameType) {
        System.out.println("Loading sortby data in to database...");

        try {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(filename);

            if (inputStream == null) {
                System.out.println("Inputstream for website data is empty....");
                return false;
            }

            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            switch (filenameType) {
                case SORT_BY:
                    for (String line; (line = reader.readLine()) != null; ) {
                        // System.out.println("SortBy Line = " + line);
                        String[] result = line.split("\\|");
                        String id = result[0];
                        String type = result[1];
                        SortByCategory sortByCategory = sortByCategoryRepository.findByType(type);
                        if (sortByCategory == null) {
                            sortByCategory = new SortByCategory(Integer.parseInt(id),type);
                            sortByCategoryRepository.save(sortByCategory);
                        }
                    }
                    break;
                case PRICE_RANGE:
                    for (String line; (line = reader.readLine()) != null; ) {
                        // System.out.println("PriceRange Line = " + line);
                        String[] result = line.split("\\|");
                        String id = result[0];
                        String type = result[1];
                        PriceRangeCategory priceRangeCategory = priceRangeCategoryRepository.findByType(type);
                        if (priceRangeCategory == null) {
                            priceRangeCategory = new PriceRangeCategory(Integer.parseInt(id),type);
                            priceRangeCategoryRepository.save(priceRangeCategory);
                        }
                    }
                    break;
                default:
                    System.out.println("filename Type unsupported..");
                    reader.close();
                    return false;
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public float generateRandomFloat(float leftLimit, float rightLimit, int places) {
        double scale = Math.pow(10, places);
        float decimalNumber = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
        return (float) (Math.round(decimalNumber * scale) / scale);
    }

    private Optional<PriceRangeCategory> findPriceRangeCategory(int price) {

        if (price <= 50) {
            return priceRangeCategoryRepository.findById(1);
        } else if (price <= 100) {
            return priceRangeCategoryRepository.findById(2);
        } else if (price <= 200) {
            return priceRangeCategoryRepository.findById(3);
        } else if (price <= 300) {
            return priceRangeCategoryRepository.findById(4);
        } else if (price <= 400) {
            return priceRangeCategoryRepository.findById(5);
        } else {
            return priceRangeCategoryRepository.findById(6);
        }
    }

    private String replaceSpacesWithUnderscore(String str) {
        return str.replaceAll("\\s", "_");
    }

    private int generateRandomInt(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private Date generateRandomDate() {
        long beginTime = Timestamp.valueOf("2019-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2020-06-31 00:00:00").getTime();
        long diff = endTime - beginTime + 1;
        return new Date(beginTime + (long) (Math.random() * diff));
    }

}
