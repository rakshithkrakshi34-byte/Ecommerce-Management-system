import { Link } from "react-router-dom";

function ProductCard({ product }) {
    if (!product) {
        return null;
    }

    const { id, name, imageUrl, price } = product;

    return (
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden hover:shadow-xl transition-shadow duration-300">
            <Link to={`/products/${id}`}>
                <img
                    src={imageUrl}
                    alt={name}
                    className="w-full h-64 object-cover"
                />

                <div className="p-5">
                    <h3 className="text-lg font-semibold text-gray-900 dark:text-white truncate">
                        {name}
                    </h3>

                    <p className="mt-2 text-xl font-bold text-blue-600 dark:text-blue-400">
                        ₹{Number(price).toLocaleString("en-IN", {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        })}
                    </p>
                </div>
            </Link>
        </div>
    );
}

export default ProductCard;
