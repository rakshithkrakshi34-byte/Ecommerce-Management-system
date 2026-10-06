import React, { useState } from "react";
import { useCart } from "../context/CartContext";
import Alert from "../components/Alert";
import axios from "../api/axios";

function CheckOut() {
    const { cartItems, subTotal } = useCart();

    const country = ["India", "USA", "Canada", "UK"];

    const [alertMessage, setAlertMessage] = useState("");
    const [alertType, setAlertType] = useState("error");

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);

        const billingDetails = {
            name: formData.get("name"),
            email: formData.get("email"),
            phone: formData.get("phone"),
            country: formData.get("country"),
            address: formData.get("address"),
            city: formData.get("city"),
            postal: formData.get("postal")
        };

        for (const [key, value] of Object.entries(billingDetails)) {
            if (!value) {
                setAlertType("error");
                setAlertMessage(`Please fill out the ${key} field.`);
                return;
            }
        }

        if (cartItems.length === 0) {
            setAlertType("error");
            setAlertMessage("Your cart is empty.");
            return;
        }

        try {
            const response = await axios.post(
                "/api/v1/stripe/create-checkout-session",
                {
                    productIds: cartItems.map(
                        item => item.product.id
                    ),
                    quantities: cartItems.map(
                        item => item.quantity
                    ),
                    successUrl: `${window.location.origin}/success`,
                    cancelUrl: `${window.location.origin}/cancel`
                },
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem(
                            "authToken"
                        )}`
                    }
                }
            );

            if (response.data?.url) {
                window.location.href = response.data.url;
            } else {
                throw new Error(
                    "Stripe checkout URL was not returned"
                );
            }
        } catch (error) {
            console.error("Checkout error:", error);

            const backendMessage =
                error.response?.data?.error ||
                "Failed to create checkout session. Please try again later.";

            setAlertType("error");
            setAlertMessage(backendMessage);
        }
    };

    return (
        <>
            <div className="flex flex-col md:justify-center md:flex-row items-start min-h-screen bg-gray-100 dark:bg-gray-900 p-4 md:p-6 gap-6 w-full overflow-x-hidden">

                {alertMessage && (
                    <Alert
                        type={alertType}
                        message={alertMessage}
                        onClose={() => setAlertMessage("")}
                    />
                )}

                <div className="w-full md:w-1/2 bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md flex-1 h-full">

                    <form
                        onSubmit={handleSubmit}
                        className="space-y-6 pb-6 text-gray-800 dark:text-gray-300"
                    >

                        <h1 className="text-2xl font-bold mb-4">
                            Billing Details
                        </h1>

                        <div>
                            <label
                                htmlFor="name"
                                className="block mb-1 font-medium"
                            >
                                Full Name
                            </label>

                            <input
                                name="name"
                                id="name"
                                type="text"
                                placeholder="Rakshith Kumar"
                                className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                            />
                        </div>

                        <div>
                            <label
                                htmlFor="email"
                                className="block mb-1 font-medium"
                            >
                                Email Address
                            </label>

                            <input
                                name="email"
                                id="email"
                                type="email"
                                placeholder="rakshith@example.com"
                                className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                            />
                        </div>

                        <div>
                            <label
                                htmlFor="phone"
                                className="block mb-1 font-medium"
                            >
                                Phone Number
                            </label>

                            <input
                                name="phone"
                                id="phone"
                                type="tel"
                                placeholder="+91 98765 43210"
                                className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                            />
                        </div>

                        <div>
                            <label
                                htmlFor="country"
                                className="block mb-1 font-medium"
                            >
                                Country
                            </label>

                            <select
                                name="country"
                                id="country"
                                className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent text-gray-800 dark:text-white focus:outline-none focus:border-blue-500"
                            >
                                <option value="">
                                    Select Country
                                </option>

                                {country.map((c, index) => (
                                    <option
                                        key={index}
                                        value={c}
                                    >
                                        {c}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div>
                            <label
                                htmlFor="address"
                                className="block mb-1 font-medium"
                            >
                                Address
                            </label>

                            <input
                                name="address"
                                id="address"
                                type="text"
                                placeholder="123 Main Street"
                                className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                            />
                        </div>

                        <div className="flex flex-col md:flex-row gap-4">

                            <div className="flex-1">
                                <label
                                    htmlFor="city"
                                    className="block mb-1 font-medium"
                                >
                                    City
                                </label>

                                <input
                                    name="city"
                                    id="city"
                                    type="text"
                                    placeholder="Bengaluru"
                                    className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                                />
                            </div>

                            <div className="flex-1">
                                <label
                                    htmlFor="postal"
                                    className="block mb-1 font-medium"
                                >
                                    Postal Code
                                </label>

                                <input
                                    name="postal"
                                    id="postal"
                                    type="text"
                                    placeholder="560001"
                                    className="w-full px-3 py-2 border-b-2 border-gray-300 dark:border-gray-600 bg-transparent focus:outline-none focus:border-blue-500"
                                />
                            </div>

                        </div>

                        <button
                            type="submit"
                            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition-colors font-semibold"
                        >
                            Place Order
                        </button>

                    </form>
                </div>

                <div className="w-full max-w-md bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md flex-1 h-full">

                    <h2 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">
                        Order Summary
                    </h2>

                    {cartItems.length === 0 ? (
                        <p className="text-gray-500 dark:text-gray-400">
                            Your cart is empty.
                        </p>
                    ) : (
                        <>
                            <ul className="space-y-4">

                                {cartItems.map((item) => (
                                    <li
                                        key={item.id}
                                        className="flex justify-between text-gray-700 dark:text-gray-300"
                                    >
                                        <span>
                                            {item.product.name}{" "}
                                            (x{item.quantity})
                                        </span>

                                        <span>
                                            ₹{Number(
                                                item.product.price *
                                                item.quantity
                                            ).toLocaleString("en-IN", {
                                                minimumFractionDigits: 2,
                                                maximumFractionDigits: 2
                                            })}
                                        </span>
                                    </li>
                                ))}

                            </ul>

                            <div className="mt-4 border-t pt-4">

                                <div className="flex justify-between text-gray-800 dark:text-white font-semibold">

                                    <span>
                                        Total
                                    </span>

                                    <span>
                                        ₹{Number(
                                            subTotal
                                        ).toLocaleString("en-IN", {
                                            minimumFractionDigits: 2,
                                            maximumFractionDigits: 2
                                        })}
                                    </span>

                                </div>

                            </div>
                        </>
                    )}

                </div>

            </div>
        </>
    );
}

export default CheckOut;
