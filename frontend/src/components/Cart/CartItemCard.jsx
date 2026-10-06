import React from "react";

function CartItemCard({ item, onRemove, onQuantityChange }) {
    return (
        <div className="flex items-center justify-between p-4 bg-white dark:bg-gray-800 rounded-lg shadow">

            <div className="flex items-center gap-4">

                <img
                    src={item.product.imageUrl}
                    alt={item.product.name}
                    className="w-20 h-20 object-cover rounded"
                />

                <div>
                    <h3 className="font-semibold text-gray-900 dark:text-white">
                        {item.product.name}
                    </h3>

                    <p className="text-blue-600 dark:text-blue-400 font-medium">
                        ₹{Number(item.product.price).toLocaleString("en-IN", {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        })}
                    </p>
                </div>

            </div>

            <div className="flex items-center gap-3">

                <button
                    type="button"
                    onClick={() =>
                        onQuantityChange(
                            item.product.id,
                            Math.max(item.quantity - 1, 1)
                        )
                    }
                    className="px-3 py-1 bg-gray-200 dark:bg-gray-700 rounded"
                >
                    -
                </button>

                <span className="text-gray-900 dark:text-white">
                    {item.quantity}
                </span>

                <button
                    type="button"
                    onClick={() =>
                        onQuantityChange(
                            item.product.id,
                            item.quantity + 1
                        )
                    }
                    className="px-3 py-1 bg-gray-200 dark:bg-gray-700 rounded"
                >
                    +
                </button>

                <button
                    type="button"
                    onClick={() => onRemove(item.product.id)}
                    className="ml-4 text-red-500 hover:text-red-700"
                >
                    Remove
                </button>

            </div>

        </div>
    );
}

export default CartItemCard;
