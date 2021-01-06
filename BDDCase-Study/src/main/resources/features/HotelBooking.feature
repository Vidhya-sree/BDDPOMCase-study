Feature: HotelBooking Application

Scenario: Free HotelBookingLogin
        Given User is on login Page
        And Verify the heading of the page
        When User enters Login Credentials
        Then User is on Hotel Booking page
        Then User enters the personal details
        Then User enters email Id and mobile number
              | user123 | user123@gmail.com |
              | 4123456 | 7037654321 |
        Then User enters guests staying and address
        Then User enters the payment details
        Then User is on Booking completed page
        