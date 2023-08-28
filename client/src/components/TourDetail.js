import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/Apis";
import { useParams } from "react-router-dom";
import { Button, Image } from "react-bootstrap";
// import FbImageGrid from "react-fb-image-grid"


const TourDetail = () => {
    const [tour, setTour] = useState(null);
    const { id } = useParams();
    // const [images, setImages] = useState(null);

    useEffect(() => {
        const loadTour = async () => {
            try {
                let e = endpoints['tourDetail'](id);

                let res = await Apis.get(e);
                setTour(res.data);
                // if (res.data.imageSet !== null) {
                //     setImages(res.data.imageSet.map(t => t.imageUrl));
                // }
                // console.log(res.data.imageSet);

            } catch (ex) {
                console.error("EX" + ex);
            }
        }

        loadTour();

    }, [id]);

    return (
        tour !== null && (
            <>
                <h1 className="text-center text-info">{tour.tourTitle}</h1>
                {/* <div>
                    {images !== null &&
                        <FbImageGrid
                            images={images}
                            hideOverlay
                            imageWidth={250}
                            imageHeight={250}
                          
                        />}
                
                    </div> */}
                <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                {tour.imageSet !== null && tour.imageSet.map(t => (
                    <Image src={t.imageUrl} width={200} height={200} />
                ))}
                </div>
                <p>{tour.tourDescription}</p>
                <Button>ĐẶT VÉ</Button>
                <h1>{tour.tourPrice} VND</h1>
            </>
        )
    )
}
export default TourDetail;