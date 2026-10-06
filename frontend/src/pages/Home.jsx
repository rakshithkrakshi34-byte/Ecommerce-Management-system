import React from "react";
import { Link } from "react-router-dom";

function Home() {
    return (
        <div className="min-h-screen bg-gray-100 dark:bg-gray-900">

            <section className="bg-white dark:bg-gray-800">
                <div className="max-w-7xl mx-auto px-6 py-16 text-center">

                    <h1 className="text-4xl md:text-6xl font-bold text-gray-900 dark:text-white">
                        Welcome to Htag Store
                    </h1>

                    <p className="mt-4 text-lg md:text-xl text-gray-600 dark:text-gray-300">
                        Discover great products at great prices.
                    </p>

                    <Link
                        to="/products"
                        className="inline-block mt-8 px-6 py-3 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700 transition"
                    >
                        Browse Products
                    </Link>

                </div>
            </section>

            <section className="max-w-7xl mx-auto px-6 py-12">

                <h2 className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-10">
                    Why Shop With Us?
                </h2>

                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

                    <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 text-center">
                        <div className="text-4xl mb-4">🛍️</div>
                        <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                            Quality Products
                        </h3>
                        <p className="mt-2 text-gray-600 dark:text-gray-300">
                            Browse a wide selection of products for everyday needs.
                        </p>
                    </div>

                    <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 text-center">
                        <div className="text-4xl mb-4">💳</div>
                        <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                            Secure Checkout
                        </h3>
                        <p className="mt-2 text-gray-600 dark:text-gray-300">
                            Secure online payments powered by Stripe.
                        </p>
                    </div>

                    <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 text-center">
                        <div className="text-4xl mb-4">🚚</div>
                        <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                            Easy Shopping
                        </h3>
                        <p className="mt-2 text-gray-600 dark:text-gray-300">
                            Add products to your cart and checkout with ease.
                        </p>
                    </div>

                </div>
            </section>

            <section className="bg-blue-600">
                <div className="max-w-7xl mx-auto px-6 py-12 text-center">

                    <h2 className="text-3xl font-bold text-white">
                        Ready to Start Shopping?
                    </h2>

                    <p className="mt-3 text-blue-100">
                        Explore our products and find something you love.
                    </p>

                    <Link
                        to="/products"
                        className="inline-block mt-6 px-6 py-3 bg-white text-blue-600 rounded-lg font-semibold hover:bg-gray-100 transition"
                    >
                        Shop Now
                    </Link>

                </div>
            </section>

        </div>
    );
}

export default Home;
