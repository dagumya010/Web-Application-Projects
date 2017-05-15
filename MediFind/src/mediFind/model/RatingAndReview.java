package mediFind.model;

public class RatingAndReview {
	
	protected int ratingAndReviewId;
	protected Users userName;
	protected HealthCareFacility mediFacility;
	protected String reviews;
	protected Float ratings;
	
	
	public RatingAndReview(int ratingAndReviewId, Users userName, HealthCareFacility mediFacility, String reviews,
			Float ratings) {
		this.ratingAndReviewId = ratingAndReviewId;
		this.userName = userName;
		this.mediFacility = mediFacility;
		this.reviews = reviews;
		this.ratings = ratings;
	}
	
	public RatingAndReview(Users userName, HealthCareFacility mediFacility, String reviews, Float ratings) {
		super();
		this.userName = userName;
		this.mediFacility = mediFacility;
		this.reviews = reviews;
		this.ratings = ratings;
	}




	public int getRatingAndReviewId() {
		return ratingAndReviewId;
	}


	public void setRatingAndReviewId(int ratingAndReviewId) {
		this.ratingAndReviewId = ratingAndReviewId;
	}


	public Users getUserName() {
		return userName;
	}


	public void setUserName(Users userName) {
		this.userName = userName;
	}


	public HealthCareFacility getMediFacility() {
		return mediFacility;
	}


	public void setMediFacility(HealthCareFacility mediFacility) {
		this.mediFacility = mediFacility;
	}


	public String getReviews() {
		return reviews;
	}


	public void setReviews(String reviews) {
		this.reviews = reviews;
	}


	public Float getRatings() {
		return ratings;
	}


	public void setRatings(Float ratings) {
		this.ratings = ratings;
	}
	
	
}

